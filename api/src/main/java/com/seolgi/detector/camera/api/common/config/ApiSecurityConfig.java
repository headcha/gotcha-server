package com.seolgi.detector.camera.api.common.config;

import com.seolgi.detector.camera.api.common.auth.*;
import com.seolgi.detector.camera.api.common.config.cookie.CustomCookieSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true , proxyTargetClass = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.headers()
					.frameOptions().disable()
			.and()
                .authorizeRequests()
				.anyRequest()
					.permitAll()
			.and().authenticationProvider(customAuthenticationProvider())
                .formLogin()
				.failureHandler(failureHandler())
				.successHandler(successHandler())
                .loginPage("/login")
                .loginProcessingUrl("/api/v1/accounts/sign-in")
                .usernameParameter("id")
                .passwordParameter("password")
                .permitAll()
            .and().logout()
                .logoutUrl("/api/v1/accounts/logout")
                .logoutSuccessUrl("/")
                .deleteCookies(CustomCookieSerializer.COOKIE_NAME , AuthToken.KEY)
                .invalidateHttpSession(true)
                .permitAll()
			.and().csrf()
                .disable()
                .httpBasic()
			.and().rememberMe()
				.key(AuthToken.SECURE_KEY)
				.tokenValiditySeconds(2592000)
				.rememberMeParameter("rememberMe")
				.rememberMeCookieName(AuthToken.KEY)
				.userDetailsService(userDetailsService())
            .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        ;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider())
				.userDetailsService(userDetailsService())
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new AuthSecurityService();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint("/");
	}

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider();
    }

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		AuthFailHandler authFailHandler = new AuthFailHandler();
		authFailHandler.setDefaultFailureUrl("/account/sign-in?loginError");
		return authFailHandler;
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		AuthSuccessHandler authSuccessHandler = new AuthSuccessHandler();
		authSuccessHandler.setDefaultTargetUrl("/");

		return authSuccessHandler;
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


	// http://docs.spring.io/spring-session/docs/current/reference/html5/guides/custom-cookie.html
	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new CustomCookieSerializer();
		serializer.setCookieName(CustomCookieSerializer.COOKIE_NAME);
		serializer.setCookiePath("/");
		return serializer;
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

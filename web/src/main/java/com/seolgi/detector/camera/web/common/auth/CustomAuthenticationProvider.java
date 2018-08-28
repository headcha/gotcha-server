package com.seolgi.detector.camera.web.common.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthSecurityService authSecurityService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		String id = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = authSecurityService.loadUserByUsername(id);

		if (user == null || passwordEncoder.matches(password , user.getPassword()) == false)
			throw new BadCredentialsException("암호가 일치하지 않습니다.");

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
		result.setDetails(user);
		return result;

	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}

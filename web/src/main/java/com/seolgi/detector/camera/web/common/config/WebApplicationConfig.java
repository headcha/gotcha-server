package com.seolgi.detector.camera.web.common.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.helper.StringHelpers;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.seolgi.detector.camera.web.common.config.helper.*;
import com.seolgi.detector.camera.web.common.config.support.MemberArgumentResolver;
import com.seolgi.detector.camera.web.common.config.xss.XssModule;
import com.seolgi.detector.camera.web.interceptor.common.CommonInterceptor;
import com.seolgi.detector.camera.web.interceptor.commonCode.CommonValueInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    @Value("${web.template.cache}")
    private boolean cache;



    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new XssModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converters.stream()
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                .map(converter -> (MappingJackson2HttpMessageConverter) converter)
                .forEach(converter -> {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    mapper.setDateFormat(simpleDateFormat);
                    converter.setObjectMapper(mapper);
                });
    }

    @Bean
    public MemberArgumentResolver memberArgumentResolver() {
        return new MemberArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(memberArgumentResolver());
    }

    @Bean
    public HandlebarsViewResolver viewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".hbs");
        viewResolver.setExposeRequestAttributes(true);
        viewResolver.setExposeSessionAttributes(true);
        viewResolver.setRequestContextAttribute("requestContext");

        viewResolver.setCache(cache);

        viewResolver.registerHelper("equals", new EqualsHelper());
        viewResolver.registerHelper("if_even", new EvenHelper());
        viewResolver.registerHelper("dateFormat", StringHelpers.dateFormat);
        viewResolver.registerHelper("numberFormat", StringHelpers.numberFormat);
        viewResolver.registerHelper("compare", new CompareHelper());
        viewResolver.registerHelper("row_counter", new RowCounterHelper());
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/resources/**")
                .addResourceLocations( "/resources/");
    }


    @Bean
    public CommonValueInterceptor commonValueInterceptor() {
        return new CommonValueInterceptor();
    }

    @Bean
    public CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonValueInterceptor()).excludePathPatterns("/api/**", "**/*.jpg");
        registry.addInterceptor(commonInterceptor()).excludePathPatterns("/api/**" , "**/*.jpg");
    }
}

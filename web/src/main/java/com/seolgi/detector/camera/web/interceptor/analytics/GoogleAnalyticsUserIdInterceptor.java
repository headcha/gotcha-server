package com.seolgi.detector.camera.web.interceptor.analytics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolgi.detector.domain.utils.UniqueUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleAnalyticsUserIdInterceptor extends HandlerInterceptorAdapter {
    
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)){
        	request.setAttribute("gaUserId" , new ObjectMapper().writeValueAsString(UniqueUtil.generate(authentication.getName())));
        }
        
		return true;
	}

}
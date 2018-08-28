package com.seolgi.detector.camera.web.interceptor.commonCode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommonValueInterceptor extends HandlerInterceptorAdapter {

    @Value("${api.image.host}")
    private String imageHost;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("imageHost", imageHost);
        return super.preHandle(request, response, handler);
    }
}
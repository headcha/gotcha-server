package com.seolgi.detector.camera.web.interceptor.common;

import com.seolgi.detector.camera.web.common.context.MemberContext;
import com.seolgi.detector.camera.web.interceptor.common.camera.CameraDto;
import com.seolgi.detector.camera.web.interceptor.common.member.MemberDto;
import com.seolgi.detector.domain.base.search.PageableFactory;
import com.seolgi.detector.domain.camera.Camera;
import com.seolgi.detector.domain.camera.CameraService;
import com.seolgi.detector.domain.member.Member;
import com.seolgi.detector.domain.utils.converter.PageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private CameraService cameraService;

    @Autowired
    private MemberContext memberContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Page<Camera> cameraPage = cameraService.findToday(PageableFactory.create(0));
        request.setAttribute("todayCameraPage", PageConverter.convert(cameraPage , CameraDto.class));

        Member loggedMember = memberContext.getLoggedMember();
        request.setAttribute("loggedMember", new MemberDto(loggedMember));
        return super.preHandle(request, response, handler);
    }
}

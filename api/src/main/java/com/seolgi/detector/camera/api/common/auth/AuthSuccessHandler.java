package com.seolgi.detector.camera.api.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seolgi.detector.domain.member.MemberService;
import com.seolgi.detector.domain.utils.UniqueUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        String accept = request.getHeader("Accept");
        String contentType = request.getHeader("Content-Type");
        memberService.updateLatestLoggedAtBy(auth.getName());


        if (isJsonByType(accept) || isJsonByType(contentType)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpStatus.OK.value());
            objectMapper.writeValue(response.getOutputStream(), new AuthSuccess(auth.getName()));
        } else {
            super.onAuthenticationSuccess(request, response, auth);
        }
    }

    private boolean isJsonByType(String contentType) {
        return contentType != null && contentType.contains("json");
    }

    @Getter
    class AuthSuccess {
        int code = 200;
        String gaUserId;

        AuthSuccess(String name) {
            gaUserId = UniqueUtil.generate(name);
        }
    }
}

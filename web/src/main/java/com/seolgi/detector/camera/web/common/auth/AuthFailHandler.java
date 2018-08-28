package com.seolgi.detector.camera.web.common.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String accept = request.getHeader("Accept");
        String contentType = request.getHeader("Content-Type");

        if (isJsonByType(accept) || isJsonByType(contentType)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            objectMapper.writeValue(response.getOutputStream(), new AuthFail("계정을 찾을 수 없습니다."));
        } else {
            super.onAuthenticationFailure(request, response, exception);

        }
    }

    private boolean isJsonByType(String contentType) {
        return contentType != null && contentType.contains("json");
    }

    @Getter
    class AuthFail {
        private int code = HttpStatus.UNAUTHORIZED.value();
        private String message;

        AuthFail(String message) {
            this.message = message;
        }
    }
}
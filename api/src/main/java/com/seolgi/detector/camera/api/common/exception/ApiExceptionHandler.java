package com.seolgi.detector.camera.api.common.exception;

import com.seolgi.detector.camera.api.ApiApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice(basePackageClasses = ApiApplication.class)
public class ApiExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResponseEntity handleInvalidRequest(ApplicationException e, HttpServletRequest request) {
        log.error("BUSINESS_LOGIC_ERROR", e);
        log.error("request info : " + request.toString());

        ApiErrorMessage message = ApiErrorMessage.create(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity internalServerError(Exception e, HttpServletRequest request) {
        log.error("INTERNAL_SERVER_ERROR", e);
        log.error("request info : " + request.toString());

        ApiErrorMessage message = ApiErrorMessage.create(500 , "일시적인 오류가 발생하였습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("accessDeniedException", e);
        log.error("request info : " + request.toString());

        ApiErrorMessage message = ApiErrorMessage.create(401 , "접근 권한이 없습니다. 로그인해 주세요");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

}
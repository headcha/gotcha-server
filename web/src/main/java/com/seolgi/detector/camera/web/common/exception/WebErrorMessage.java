package com.seolgi.detector.camera.web.common.exception;

import lombok.Getter;

@Getter
public class WebErrorMessage {
    private int code;
    private String message;

    public static WebErrorMessage create(int code, String message) {
        WebErrorMessage apiErrorMessage = new WebErrorMessage();
        apiErrorMessage.code = code;
        apiErrorMessage.message = message;
        return apiErrorMessage;
    }
}
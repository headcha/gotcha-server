package com.seolgi.detector.camera.api.common.exception;

import lombok.Getter;

@Getter
public class ApiErrorMessage {
    private int code;
    private String message;

    public static ApiErrorMessage create(int code, String message) {
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage();
        apiErrorMessage.code = code;
        apiErrorMessage.message = message;
        return apiErrorMessage;
    }
}
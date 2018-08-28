package com.seolgi.detector.camera.web.common.exception;

import lombok.Getter;

public class ApplicationException extends RuntimeException {
    @Getter
    private int code = 501;


    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(int code , String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(int code , String message , Throwable cause) {
        super(message , cause);
        this.code = code;
    }
}

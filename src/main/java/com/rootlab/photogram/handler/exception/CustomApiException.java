package com.rootlab.photogram.handler.exception;

public class CustomApiException extends RuntimeException {
    private static final long serialVersionUID = -2523117237385538277L;

    public CustomApiException(String message) {
        super(message);
    }
}

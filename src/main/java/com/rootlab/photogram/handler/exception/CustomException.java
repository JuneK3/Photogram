package com.rootlab.photogram.handler.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -518042541348606621L;

    public CustomException(String message) {
        super(message);
    }
}

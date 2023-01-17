package com.rootlab.photogram.handler.exception;

import java.util.Map;

public class CustomValidationException extends Throwable {

    private static final long serialVersionUID = 686704829056468809L;

    private final Map<String, String> errorMap;

    public CustomValidationException(String message ,Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return this.errorMap;
    }
}

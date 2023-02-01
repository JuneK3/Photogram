package com.rootlab.photogram.handler.exception;

import java.util.Map;

public class CustomValidationApiException extends Throwable {

    private static final long serialVersionUID = -5728269290540476506L;
    private final Map<String, String> errorMap;

    public CustomValidationApiException(String message , Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return this.errorMap;
    }
}

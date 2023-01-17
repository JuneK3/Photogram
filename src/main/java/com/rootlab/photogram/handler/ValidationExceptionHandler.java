package com.rootlab.photogram.handler;

import com.rootlab.photogram.handler.exception.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public Map<String, String> validationExceptionHandler(CustomValidationException e) {
        return e.getErrorMap();
    }
}

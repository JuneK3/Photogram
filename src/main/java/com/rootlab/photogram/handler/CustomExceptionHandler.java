package com.rootlab.photogram.handler;

import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
import com.rootlab.photogram.handler.exception.CustomException;
import com.rootlab.photogram.handler.exception.CustomValidationApiException;
import com.rootlab.photogram.handler.exception.CustomValidationException;
import com.rootlab.photogram.util.GoBackToPreviousPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationExceptionHandler(CustomValidationException e) {

        if (e.getErrorMap() == null) {
            return GoBackToPreviousPage.alert(e.getMessage());
        }
        return GoBackToPreviousPage.alert(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomException.class)
    public String exceptionHandler(CustomException e) {
        return GoBackToPreviousPage.alert(e.getMessage());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiExceptionHandler(CustomValidationApiException e) {
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiExceptionHandler(CustomApiException e) {
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}

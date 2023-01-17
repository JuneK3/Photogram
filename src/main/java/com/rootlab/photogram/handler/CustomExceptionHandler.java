package com.rootlab.photogram.handler;

import com.rootlab.photogram.dto.CommonResponseDto;
import com.rootlab.photogram.handler.exception.CustomApiException;
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
//        return new CommonResponseDto<>(-1, e.getMessage(), e.getErrorMap());
        return GoBackToPreviousPage.alert(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CommonResponseDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}

package com.rootlab.photogram.handler;

import com.rootlab.photogram.handler.exception.CustomValidationException;
import com.rootlab.photogram.util.GoBackToPreviousPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationExceptionHandler(CustomValidationException e) {
//        return new CommonResponseDto<>(-1, e.getMessage(), e.getErrorMap());
        return GoBackToPreviousPage.alert(e.getErrorMap().toString());
    }

    // 호출되지 않음
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public String loginExceptionHandler(UsernameNotFoundException e) {
//        log.info("[AuthExceptionHandler] loginExceptionHandler 호출: " + e.getMessage());
//        return GoBackToPreviousPage.alert(e.getMessage());
//    }

}

package com.rootlab.photogram.controller;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.auth.SignUpDto;
import com.rootlab.photogram.handler.exception.CustomValidationException;
import com.rootlab.photogram.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signup")
    public String signUpPage() {
        return "auth/signup";
    }

    @GetMapping("/auth/signin")
    public String signInPage() {
        return "auth/signin";
    }


    @PostMapping("/auth/signup")
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult)
            throws CustomValidationException {

        // x-www-form-urlencoded

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        }

        User user = signUpDto.toEntity();
        authService.saveUser(user);
//        return "auth/signin";
        return "redirect:/auth/signin";
    }

}

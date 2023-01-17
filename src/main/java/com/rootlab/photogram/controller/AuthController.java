package com.rootlab.photogram.controller;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.auth.SignUpDto;
import com.rootlab.photogram.handler.exception.CustomValidationException;
import com.rootlab.photogram.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signup")

    public String signUpPage() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult)
            throws CustomValidationException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        }

        // @RequestBody가 없기 때문에 request body의 데이터로 dto 객체를 초기화하지 못함
        // x-www-form-urlencoded 방식으로만 dto 객체에 값을 넣어줄 수 있음

        User user = signUpDto.toEntity();
        authService.saveUser(user);

//        return "redirect:/auth/signin";
        return "auth/signin";
    }

}

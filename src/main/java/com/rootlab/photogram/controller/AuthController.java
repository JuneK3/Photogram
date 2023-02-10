package com.rootlab.photogram.controller;

import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.dto.auth.SignUpDto;
import com.rootlab.photogram.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult) {
        User user = signUpDto.toEntity();
        authService.saveUser(user);
        return "redirect:/auth/signin";
    }

}

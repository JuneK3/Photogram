package com.rootlab.photogram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	Logger log = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/auth/signup")
	public String signUpPage() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signUp() {
		log.info("POST /auth/signup 요청에 signUp 실행됨");
		return "auth/signin";
	}

}

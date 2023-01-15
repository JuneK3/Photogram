package com.rootlab.photogram.controller;

import com.rootlab.photogram.dto.auth.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthController {

	@GetMapping("/auth/signup")
	public String signUpPage() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signUp(SignUpDto signUpDto) {
		
		// @RequestBody가 없기 때문에 request body의 데이터로 dto 객체를 초기화하지 못함
		// x-www-form-urlencoded 방식으로만 dto 객체에 값을 넣어줄 수 있음
		
		log.info("POST /auth/signup 요청에 signUp 실행됨");
		log.info(signUpDto.toString());
		return "auth/signin";
	}

}

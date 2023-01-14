package com.rootlab.photogram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/jsp")
	public String testJspFile() {
		return "test";
	}
}

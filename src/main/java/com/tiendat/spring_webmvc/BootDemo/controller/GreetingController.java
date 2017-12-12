package com.tiendat.spring_webmvc.BootDemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	@RequestMapping("/greeting")
	public String greeting() {
		return "HIHI - This is DAt";
	}
}

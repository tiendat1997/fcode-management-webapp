package com.tiendat.spring_webmvc.BootDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	// defaul to login 
	@RequestMapping("/")
	public String firstPage() {		
		return "redirect:/account";		
	}
}

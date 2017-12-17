package com.tiendat.spring_webmvc.BootDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
		
	@GetMapping(value = "/dashboard")
	public String getDashboardPage(ModelMap modelMap) {
		return "adminHome";
	}
	
	@GetMapping(value = "/member")
	public String getMemberPage(ModelMap modelMap) {
		return "adminMember";
	}
	
	@GetMapping(value = "/member/current")
	public String getMemberCurrentPage(ModelMap modelMap) {
		System.out.println("Go to Member Current");
		return "adminMemberCurrent";
	}
	@GetMapping(value = "/member/expired")
	public String getMemberExpiredPage(ModelMap modelMap) {
		return "adminMemberExpired";
	}	
	
	@GetMapping(value = "/member/grade")
	public String getMemberGradePage(ModelMap modelMap) {
		return "adminMemberGrade";
	}
	
	
	@GetMapping(value = "/event")
	public String getEventPage(ModelMap modelMap) {
		return "adminEvent";
	}
	
	@GetMapping(value = "/project")
	public String getProjectPage(ModelMap modelMap) {
		return "adminProject";		
	}
	
	
	
	
}

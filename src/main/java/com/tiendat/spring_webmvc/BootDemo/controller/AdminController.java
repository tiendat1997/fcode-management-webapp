package com.tiendat.spring_webmvc.BootDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private AccountService accountService; 
		
	@GetMapping(value = "/dashboard")
	public String getDashboardPage(ModelMap modelMap) {
		return "adminHome";
	}
	
	@GetMapping(value = "/member")
	public String getMemberPage(ModelMap modelMap) {
		return "adminMember";
	}
	@GetMapping(value = "/member/update", params = {"username"})
	public ModelAndView updateMemberPage(@RequestParam("username") String username) {
		Account member = this.accountService.findByUsername(username); 
		ModelAndView mv = new ModelAndView("adminMemberUpdate"); 
		mv.addObject("member", member);
				
		return mv;
	}
	@PostMapping(value = "/member/update")
	public ModelAndView updateMemberPage(@ModelAttribute("member") Account member) {
						
		int result = this.accountService.updateMemberAdmin(
					member.getUsername(), member.getFullname(), member.getGrade(), 
					member.getMajor(), member.getEmail(), member.getPhone());
		
		ModelAndView mv = null;
		if (result > 0) {
			mv = new ModelAndView("adminMember");
		}
		else {
			mv = new ModelAndView("adminMemberUpdate");			
		}		
		return mv; 					
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

package com.tiendat.spring_webmvc.BootDemo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;

@Controller
@RequestMapping("account")
public class AccountController {
	
	@Autowired
	private AccountService accountService; 
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String doLoginPage(ModelMap modelMap) {
		modelMap.put("account", new Account());				
		return "index"; 
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(
			@ModelAttribute("account") Account account, 
			ModelMap modelMap,
			HttpSession session) {
		
		account = accountService.findByUsernameAndPassword(
					account.getUsername(),
					account.getPassword()); 
		
		if ( account != null) {
				session.setAttribute("account", account);
				
				Role role = this.accountService.findUserRole(account.getRoleId());
				if (role.getRoleId() == 1) {
					// Admin
					return "redirect:/admin/dashboard";
					
					
				} else if (role.getRoleId() == 2){
					// Normal User
					return "welcome";
				}
					
			return "index";
			
		} else {
			modelMap.put("error", "Account's Invalid");
			return "index";
			
		}		
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:../account";
	}
}

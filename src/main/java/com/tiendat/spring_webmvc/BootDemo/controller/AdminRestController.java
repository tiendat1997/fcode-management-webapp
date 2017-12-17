package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.service.AccountService;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {
	
	@Autowired
	private AccountService accountService; 
	
	// GetAccount not expired
	@GetMapping(value = "/member/current")
	public List<Account> getAllAccountNotExpired(){
		return this.accountService.findAllAccountByExpired(false);
	}
	
	// GetAccount expired
		@GetMapping(value = "/member/expired")
		public List<Account> getAllAccountExpired(){
			return this.accountService.findAllAccountByExpired(true);
		}
		
	//GetAccount by grade
		@GetMapping(value = "/member/grade/{grade}")
		public List<Account> getAccountByGrad(@PathVariable String grade) {
			return this.accountService.findAccountByGrade(Integer.parseInt(grade));
		}
	
	@GetMapping(value = "/account/get", params = {"page", "size"})
	public Page<Account> findAccountPaginate(
				@RequestParam("page") int page, 
				@RequestParam("size") int size
			){
		
		Page<Account> resultPage = this.accountService.findAccountPaginated(page, size);
		
		// check if not found
		if (page > resultPage.getTotalPages()) {
			return null;
		}
		
		return resultPage;		
	}
	
	@GetMapping(value = "/account/filter", params = {"username", "fullname", "email", "phone", "grade","page","size"})
	public Page<Account> filterAccountPaginate(
				@RequestParam("username") String username,
				@RequestParam("fullname") String fullname, 
				@RequestParam("email") String email,
				@RequestParam("phone") String phone, 	
				@RequestParam("grade") Integer grade,
				@RequestParam("page") int page, 
				@RequestParam("size") int size				
			){
		Page<Account> resultPage = this.accountService.filterAccountPaginated(username, fullname, email, phone, grade,page, size);
		if (page > resultPage.getTotalPages()) {
			return null; 
		}
		
		return resultPage;
	}
	
	@GetMapping(value = "/member/delete", params= {"username"})
	public Account deleteAccount(@RequestParam("username") String username) {	
		return this.accountService.deleteAccount(username);
	}
	
	@GetMapping(value = "/member/restore", params= {"username"})
	public Account restoreAccount(@RequestParam("username") String username) {	
		return this.accountService.restoreAccount(username);
	}
	
	@GetMapping(value = "/member/update")
	public Account updateAccount(@ModelAttribute Account account) {
		return this.accountService.updateAccount(account);
	}
	
	
}	

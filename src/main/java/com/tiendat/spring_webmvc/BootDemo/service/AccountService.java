package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;

public interface AccountService {

	Role findUserRole(int roleId);

	Account findByUsernameAndPassword(String username, String password);
	
	List<Account> findAllAccountByExpired(boolean expired);
	List<Account> findAccountByGrade(int grade);
	Page<Account> findAccountPaginated(int page, int size);
}
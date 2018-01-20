package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;
import com.tiendat.spring_webmvc.BootDemo.model.UserAccount;

public interface AccountService {

	Role findUserRole(int roleId);

	Account findByUsernameAndPassword(String username, String password);
	Account findByUsername(String username);
	Account addAccount(Account account);
	Account deleteAccount(String username);
	Account restoreAccount(String username);
	Account updateAccount(Account account);
	Account addNewAccount(Account account);
	
	List<Account> findAllAccountByExpired(boolean expired);
	List<Account> findAccountByGrade(int grade);
	Page<Account> findAccountPaginated(int option,int page, int size);
	boolean accountExisted(String username);
	
	int updateMemberAdmin(String username, String fullname, Integer grade, String major, String email, String phone);
	
	Page<Account> filterAccountPaginated(
			String username,
			String fullname,
			String email, 
			String phone,
			Integer grade,
			int option,
			int page, int size);
	
	List<Account> findByRoleId(int roleId);
	
	List<Account> findAdminAndModerator(int roleAdmin, int roleModerator);
	List<Account> findTop10ByFullname(String fullname);
	List<UserAccount> findTop10ByFullnameForUser(String fullname);
	
	int countMemberByRoleId(int roleId);
}
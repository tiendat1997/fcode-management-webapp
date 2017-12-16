package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;
import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.RoleRespository;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRespository;
	
	@Autowired	
	private RoleRespository roleRespository;
	
	/* (non-Javadoc)
	 * @see com.tiendat.spring_webmvc.BootDemo.service.AccountService#findUserRole(int)
	 */
	@Override
	public Role findUserRole(int roleId) {
		return this.roleRespository.findByRoleId(roleId);
	}
	
	/* (non-Javadoc)
	 * @see com.tiendat.spring_webmvc.BootDemo.service.AccountService#findByUsernameAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Account findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return this.accountRespository.findByUsernameAndPassword(username, password);
	}
	
	
	// Get All Account that don't be expired
	@Override
	public List<Account> findAllAccountByExpired(boolean expired){
		return this.accountRespository.findByExpired(expired);
	}
	
	// this shoudl be non-expired account
	@Override
	public Page<Account> findAccountPaginated(int page, int size){
		return this.accountRespository.findAll(new PageRequest(page, size));
	}

	@Override
	public List<Account> findAccountByGrade(int grade) {
		
		return this.accountRespository.findByGrade(grade);
	}

	@Override
	public Account findByUsername(String username) {
		return accountRespository.findByUsername(username);
	}

	@Override
	public Account addAccount(Account account) {
		return accountRespository.save(account);
	}

	@Override
	public Account deleteAccount(String username) {
		Account account = accountRespository.findByUsername(username);
		account.setExpired(true);
		return accountRespository.save(account);
	}

	@Override
	public Account restoreAccount(String username) {
		Account account = accountRespository.findByUsername(username);
		account.setExpired(false);
		return accountRespository.save(account);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRespository.save(account);
	}

}

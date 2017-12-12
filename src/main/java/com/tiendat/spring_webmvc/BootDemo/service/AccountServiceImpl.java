package com.tiendat.spring_webmvc.BootDemo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

}

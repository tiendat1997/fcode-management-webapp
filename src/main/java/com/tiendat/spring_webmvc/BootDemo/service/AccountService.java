package com.tiendat.spring_webmvc.BootDemo.service;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Role;

public interface AccountService {

	Role findUserRole(int roleId);

	Account findByUsernameAndPassword(String username, String password);

}
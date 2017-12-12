package com.tiendat.spring_webmvc.BootDemo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUsernameAndPassword(String username, String password);
	Account findByUsername(String username);
			
}

package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUsernameAndPassword(String username, String password);
	Account findByUsername(String username);
	
	Page<Account> findByExpired(boolean expired, Pageable pageable);
	List<Account> findByExpired(boolean expired);
	List<Account> findByGrade(Integer grade);
	
	Page<Account> findAll(Pageable pageable);
}

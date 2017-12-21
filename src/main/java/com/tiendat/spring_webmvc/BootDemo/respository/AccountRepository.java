package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tiendat.spring_webmvc.BootDemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUsernameAndPassword(String username, String password);
	Account findByUsername(String username);
	Account save(Account account);	
	
	Page<Account> findByExpired(boolean expired, Pageable pageable);
	List<Account> findByExpired(boolean expired);
	List<Account> findByGrade(Integer grade);
	
	
	
	Page<Account> findAll(Pageable pageable);
	
	@Query("SELECT a FROM member a "
			+ "WHERE a.username LIKE %:username% "
			+ "AND a.fullname LIKE %:fullname% "
			+ "AND a.email LIKE %:email% "
			+ "AND a.phone LIKE %:phone% "
			+ "AND a.grade = :grade")
	Page<Account> filterAccount(
			@Param("username") String username,
			@Param("fullname") String fullname,
			@Param("email") String email,
			@Param("phone") String phone,
			@Param("grade") Integer grade,
			Pageable pageable);
	
	@Query("SELECT a FROM member a "
			+ "WHERE a.username LIKE %:username% "
			+ "AND a.fullname LIKE %:fullname% "
			+ "AND a.email LIKE %:email% "
			+ "AND a.phone LIKE %:phone% ")
	Page<Account> filterAccount(
			@Param("username") String username,
			@Param("fullname") String fullname,
			@Param("email") String email,
			@Param("phone") String phone,			
			Pageable pageable);
	
	
	
}
package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tiendat.spring_webmvc.BootDemo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByUsernameAndPassword(String username, String password);
	Account findByUsername(String username);
	String findUsernameByStudentCode(String studentCode);
	Account save(Account account);	
	
	
	@Modifying
	@Query("UPDATE member a "
			+ "SET a.fullname = :fullname, a.grade = :grade, a.major = :major, a.email = :email, a.phone = :phone "
			+ "WHERE a.username = :username")
	int updateMemberAdmin(
			@Param("username") String username, 
			@Param("fullname") String fullname,
			@Param("grade") Integer grade, 
			@Param("major") String major,
			@Param("email") String email,
			@Param("phone") String phone
			);
	
	Page<Account> findByExpired(boolean expired, Pageable pageable);
	List<Account> findByExpired(boolean expired);
	List<Account> findByGrade(Integer grade);
	
	
	
	Page<Account> findAll(Pageable pageable);
	
	@Query("SELECT a FROM member a "
			+ "WHERE a.expired = :expired")
	Page<Account> findAll(@Param("expired") boolean expired, Pageable pageable);
	
	
	@Query("SELECT a FROM member a "
			+ "WHERE a.username LIKE %:username% "
			+ "AND a.fullname LIKE %:fullname% "
			+ "AND a.email LIKE %:email% "
			+ "AND a.phone LIKE %:phone% "
			+ "AND a.grade = :grade "
			+ "AND a.expired = :expired")
	Page<Account> filterAccount(
			@Param("username") String username,
			@Param("fullname") String fullname,
			@Param("email") String email,
			@Param("phone") String phone,
			@Param("grade") Integer grade,
			@Param("expired") boolean expired,
			Pageable pageable);
	
	
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
	
	
	
	// Not Query Grade 
	@Query("SELECT a FROM member a "
			+ "WHERE a.username LIKE %:username% "
			+ "AND a.fullname LIKE %:fullname% "
			+ "AND a.email LIKE %:email% "
			+ "AND a.phone LIKE %:phone% "
			+ "AND a.expired = :expired")
	Page<Account> filterAccount(
			@Param("username") String username,
			@Param("fullname") String fullname,
			@Param("email") String email,
			@Param("phone") String phone,
			@Param("expired") boolean expired,
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
	
	
	List<Account> findByRoleId(int roleId);
	
	@Query("SELECT a From member a "
			+ "Where a.roleId = :roleAdmin OR a.roleId = :roleModerator")
	List<Account> findModeratorAndAdmin(
			@Param("roleAdmin") int roleAdmin,
			@Param("roleModerator") int roleModerator);
	
	List<Account> findTop10ByFullnameContaining(String fullname);
	List<Account> findTop10ByUsernameContaining(String username);
	
	int countByRoleId(int roleId);
	
}

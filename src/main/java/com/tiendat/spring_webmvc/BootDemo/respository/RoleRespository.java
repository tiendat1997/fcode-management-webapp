package com.tiendat.spring_webmvc.BootDemo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Role;

public interface RoleRespository extends JpaRepository<Role, Integer>{
	Role findByRoleId(int roleId);
	

}

package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	List<Project> findAll();
	List<Project> findByMemberId(String memberId);
	List<Project> findByNotPublicIsFalse();
	List<Project> findByNotPublicIsTrue();
	
	Project findByProjectId(int projectId);
	
	Project save(Project project);
	
	
}

package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tiendat.spring_webmvc.BootDemo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

	List<Project> findAll();
	
	List<Project> findByNotPublicIsFalse();
	List<Project> findByNotPublicIsTrue();
	
	Project findByProjectId(int projectId);
	
	Project save(Project project);
	Project saveAndFlush(Project project);
	
//	************USER***********************
	List<Project> findByMemberId(String memberId);
	@Query("Select p " + 
			"from project p " + 
			"where projectId IN ( " + 
									"select projectId " + 
									"from project_member " + 
									"where memberId = ?1) " + 
			"order by projectId")
	List<Project> findProjectParticipant(String memberId);
	
	
}

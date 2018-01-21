package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.ProjectMember;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>{

	List<ProjectMember> findByProjectId(int projectId);
	List<ProjectMember> findByProjectIdAndMemberId(int projectId,String memberId);
	
	ProjectMember save(ProjectMember member);
	Long deleteByProjectId(int projectId);
	Long deleteByProjectIdAndMemberId(int projectId, String memberId);
}

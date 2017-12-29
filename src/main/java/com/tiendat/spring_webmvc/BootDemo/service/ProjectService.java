package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;

public interface ProjectService {

	List<ProjectInformation> findAllProject();
	List<ProjectInformation> findPublicProject();
	List<ProjectInformation> findNotPublicProject();
	ProjectInformation findProjectById(int projectId);
	List<ProjectInformation> findMemberProject(String memberId);
	List<ProjectInformation> findProjectByCategory(int categoryId);
	
	boolean addProject(Project project, List<Integer> categories, List<String> members);
	boolean approveProject(int projectId, boolean notPublic);
	
	
}

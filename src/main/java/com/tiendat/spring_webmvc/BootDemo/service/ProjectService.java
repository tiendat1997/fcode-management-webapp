package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.List;

import com.tiendat.spring_webmvc.BootDemo.model.Category;
import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;

public interface ProjectService {

	List<ProjectInformation> findAllProject();
	List<ProjectInformation> findPublicProject();
	List<ProjectInformation> findNotPublicProject();
	ProjectInformation findProjectById(int projectId);
	
	List<ProjectInformation> findProjectByCategory(int categoryId);
	
	boolean addProject(Project project, int[] categories, String[] members);
	boolean addProject(Project project, int[] categories);
	boolean approveProject(int projectId, boolean notPublic);
	
//****************USER*********************
	List<ProjectInformation> findMemberProject(String memberId);
	List<ProjectInformation> getListProjectParticipant(String memberId);
	List<Category> getListCategory();
	
	boolean updateProject(Project project, int[] categories, String[] members);
	
}

package com.tiendat.spring_webmvc.BootDemo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendat.spring_webmvc.BootDemo.model.ProjectCategory;

public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long>{

	List<ProjectCategory> findByProjectId(int projectId);
	List<ProjectCategory> findByCategoryId(int categoryId);
	List<ProjectCategory> findAll();
	
	ProjectCategory save(ProjectCategory projectCategory);
	Long deleteByProjectId(int projectId);
	
}

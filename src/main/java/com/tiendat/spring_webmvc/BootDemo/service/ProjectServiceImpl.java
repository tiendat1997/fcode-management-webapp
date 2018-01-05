package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectCategory;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectMember;
import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.CategoryRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.ProjectCategoryRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.ProjectMemberRepository;
import com.tiendat.spring_webmvc.BootDemo.respository.ProjectRepository;

@Service("projectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryRepository categoryReposity;

    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private AccountRepository accountRepository;

    private ProjectInformation findAllInfomation(Project project) {

        if (project != null) {
            ProjectInformation projectInformation = new ProjectInformation();

            projectInformation.setProject(project);

            List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(project.getProjectId());

            if (projectMembers != null) {
                for (ProjectMember projectMember : projectMembers) {
                    projectInformation.addMember(accountRepository.findByUsername(projectMember.getMemberId()));
                }
            }

            List<ProjectCategory> projectCategories = projectCategoryRepository.findByProjectId(project.getProjectId());
            if (projectCategories != null) {
                for (ProjectCategory projectCategory : projectCategories) {
                    projectInformation.addCategory(categoryReposity.findByCategoryId(projectCategory.getCategoryId()));
                }

            }

            return projectInformation;
        }

        return null;
    }

    private List<ProjectInformation> findListAllInformation(List<Project> projects){
    	List<ProjectInformation> listProjectInformation = null;
        for (Project project : projects) {
            if (listProjectInformation == null) {
                listProjectInformation = new ArrayList<>();
            }
            listProjectInformation.add(findAllInfomation(project));
        }
        return listProjectInformation;
    }
    @Override
    public List<ProjectInformation> findAllProject() {
        List<Project> projects = projectRepository.findAll();
        return findListAllInformation(projects);
    }

    @Override
    public List<ProjectInformation> findPublicProject() {
        List<Project> projects = projectRepository.findByNotPublicIsFalse();
        return findListAllInformation(projects);
    }

    @Override
    public ProjectInformation findProjectById(int projectId) {
        return findAllInfomation(projectRepository.findByProjectId(projectId));
    }

    @Override
    public List<ProjectInformation> findMemberProject(String memberId) {
        List<Project> projects = projectRepository.findByMemberId(memberId);
       return findListAllInformation(projects);

    }

    @Override
    public List<ProjectInformation> findProjectByCategory(int categoryId) {
        List<ProjectCategory> projectCategories = projectCategoryRepository.findByCategoryId(categoryId);
        List<ProjectInformation> listProjectInformation = null;
        for (ProjectCategory projectCategory : projectCategories) {
            if (listProjectInformation == null) {
                listProjectInformation = new ArrayList<>();
            }
            listProjectInformation.add(findAllInfomation(projectRepository.findByProjectId(projectCategory.getProjectId())));
        }

        return listProjectInformation;
    }

    @Override
    public boolean addProject(Project project, List<Integer> categories, List<String> members) {
        // TODO Auto-generated method stub
        return false;
    }

	@Override
	public List<ProjectInformation> findNotPublicProject() {
		List<Project> projects = projectRepository.findByNotPublicIsTrue();
		return this.findListAllInformation(projects);
	}

	@Override
	public boolean approveProject(int projectId, boolean notPublic) {
		Project project = projectRepository.findByProjectId(projectId);
		if (project != null) {
			project.setNotPublic(notPublic);
			projectRepository.save(project);
			return true;
		}
		
		return false;
	}

	@Override
	public List<ProjectInformation> getListProjectParticipant(String memberId) {
		List<Project> projects = projectRepository.findProjectParticipant(memberId);
		List<ProjectInformation> projectsParticipant = this.findListAllInformation(projects);
		return projectsParticipant;
	}

	

}

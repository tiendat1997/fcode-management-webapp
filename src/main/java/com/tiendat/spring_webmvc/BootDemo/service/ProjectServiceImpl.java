package com.tiendat.spring_webmvc.BootDemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.model.Category;
import com.tiendat.spring_webmvc.BootDemo.model.Project;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectCategory;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectInformation;
import com.tiendat.spring_webmvc.BootDemo.model.ProjectMember;
import com.tiendat.spring_webmvc.BootDemo.model.UserAccount;
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

	@Autowired
	private CategoryRepository categoryRepository;

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

	private List<ProjectInformation> findListAllInformation(List<Project> projects) {
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
			listProjectInformation
					.add(findAllInfomation(projectRepository.findByProjectId(projectCategory.getProjectId())));
		}

		return listProjectInformation;
	}

	@Override
	public boolean addProject(Project project, int[] categories) {
		project = this.projectRepository.saveAndFlush(project);
		System.out.println(project.getProjectId());
		for (int i : categories) {
			System.out.println(i);
		}

		int projectId = project.getProjectId();
		for (int i = 0; i < categories.length; i++) {
			this.projectCategoryRepository.save(new ProjectCategory(projectId, categories[i]));
		}
		return true;
	}

	@Override
	public boolean addProject(Project project, int[] categories, String[] members) {
		this.projectRepository.saveAndFlush(project);
		for (int i = 0; i < categories.length; i++) {
			this.projectCategoryRepository.save(new ProjectCategory(project.getProjectId(), categories[i]));
		}
		for (int i = 0; i < members.length; i++) {
			this.projectMemberRepository.save(new ProjectMember(members[i], project.getProjectId()));
		}
		return true;
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

	@Override
	public List<Category> getListCategory() {
		return this.categoryRepository.findAll();
	}

	@Override
	public boolean updateProject(Project project, int[] categories, String[] members) {
		this.projectRepository.save(project);
		this.projectCategoryRepository.deleteByProjectId(project.getProjectId());
		this.projectMemberRepository.deleteByProjectId(project.getProjectId());
		for (int i = 0; i < categories.length; i++) {
			this.projectCategoryRepository.save(new ProjectCategory(project.getProjectId(), categories[i]));
		}
		for (int i = 0; i < members.length; i++) {
			this.projectMemberRepository.save(new ProjectMember(members[i], project.getProjectId()));
		}
		return true;
	}

	@Override
	public boolean addCollaborators(String[] members, int projectId) {
		for (String member : members) {
			List<ProjectMember> projectMember = projectMemberRepository.findByProjectIdAndMemberId(projectId, member);
			
			if (projectMember.isEmpty()) {
				if(!member.equals(projectRepository.findByProjectId(projectId).getMemberId())){
					ProjectMember pm = projectMemberRepository.save(new ProjectMember(member, projectId));
					if (pm == null) {
						return false;
					}
				}else {
					return false;
				}
				
			}else {
				return false;
			}
			
		}
		return true;
	}

	@Override
	public boolean deleteCollaborator(String member, int projectId) {
		long result = projectMemberRepository.deleteByProjectIdAndMemberId(projectId, member);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserAccount> getListProjectCollaborators(int projectId) {
		List<ProjectMember> list = projectMemberRepository.findByProjectId(projectId);
		List<UserAccount> listAccount = null;
		for (ProjectMember pm : list) {
			if (listAccount == null) {
				listAccount = new ArrayList<>();
			}
			Account account = accountRepository.findByUsername(pm.getMemberId());
			listAccount.add(new UserAccount(account.getUsername(), account.getFullname(), account.getStudentCode()));
		}
		return listAccount;
	}

	@Override
	public boolean addCollaboratorsUsingStudentCode(String[] studentCodes, int projectId) {
		for (String studentCode : studentCodes) {
			String username = accountRepository.findUsernameByStudentCode(studentCode).get(0).getUsername();

			ProjectMember pm = projectMemberRepository.save(new ProjectMember(username, projectId));
			if (pm == null) {
				return false;
			}
		}
		return true;

	}

}

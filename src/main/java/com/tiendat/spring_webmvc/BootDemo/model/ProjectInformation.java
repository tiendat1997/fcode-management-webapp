package com.tiendat.spring_webmvc.BootDemo.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectInformation {

	private Project project;
	private List<Account> members;
	private List<Category> categories;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Account> getMembers() {
		return members;
	}

	public void setMembers(List<Account> members) {
		this.members = members;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void addMember(Account member) {
		if (this.members == null)
			this.members = new ArrayList<>();
		if (!this.members.contains(member))
			this.members.add(member);
	}
	
	public void addCategory(Category category) {
		if (this.categories == null)
			this.categories = new ArrayList<>();
		if (!this.categories.contains(category))
			this.categories.add(category);
	}
	
}

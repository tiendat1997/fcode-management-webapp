package com.tiendat.spring_webmvc.BootDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "project_member")
public class ProjectMember {

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String memberId;
	private int projectId;

	
	
	public ProjectMember() {
		super();
	}

	public ProjectMember(String memberId, int projectId) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

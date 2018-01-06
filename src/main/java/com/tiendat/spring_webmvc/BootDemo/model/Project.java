package com.tiendat.spring_webmvc.BootDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "project")
public class Project {

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projectId;
	private String name;
	private String description;
	private String memberId;
	private String link;
	private String imgUrl;
	private boolean notPublic;

	
	
	public Project() {
		super();
	}

	public Project(int projectId, String name, String description, String memberId, String link, String imgUrl,
			boolean notPublic) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.memberId = memberId;
		this.link = link;
		this.imgUrl = imgUrl;
		this.notPublic = notPublic;
	}

	public Project(String name, String description, String memberId, String link, String imgUrl, boolean notPublic) {
		super();
		this.name = name;
		this.description = description;
		this.memberId = memberId;
		this.link = link;
		this.imgUrl = imgUrl;
		this.notPublic = notPublic;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isNotPublic() {
		return notPublic;
	}

	public void setNotPublic(boolean notPublic) {
		this.notPublic = notPublic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

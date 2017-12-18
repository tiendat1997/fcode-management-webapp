package com.tiendat.spring_webmvc.BootDemo.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "action_post")
public class PostAction {

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String memberId;
	private int postId;
	private int action;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	
	
	public PostAction(String memberId, int postId, int action, Date date) {
		super();
		this.memberId = memberId;
		this.postId = postId;
		this.action = action;
		this.date = date;
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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

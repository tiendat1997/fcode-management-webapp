package com.tiendat.spring_webmvc.BootDemo.model;

public class UserAccount {

	private String username;
	private String fullName;
	private String studentCode;
	public UserAccount(String username, String fullName, String studentCode) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.studentCode = studentCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	
	
	
}

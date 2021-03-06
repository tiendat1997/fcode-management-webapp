package com.tiendat.spring_webmvc.BootDemo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "member")
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//    @GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private String username; 
	
	
	@JsonIgnore
	private String password;
	
	private String fullname;
	
//	@JsonIgnore
	private String email; 
	private String phone;
	
	
	private Integer roleId;
	private String imageUrl; 
	private String studentCode; 
		
	private Integer grade;
	private String major; 
	private boolean expired; 
	
	public Account() {
		
	}
	
	
	
	public Account(String username, String password, String fullname, String email, String phone, Integer roleId,
			String studentCode, boolean expired) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.studentCode = studentCode;		
		this.expired = expired;
	}



	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", fullname=" + fullname + ", email="
				+ email + ", phone=" + phone + ", roleId=" + roleId + ", imageUrl=" + imageUrl + ", studentCode="
				+ studentCode + ", grade=" + grade + ", major=" + major + ", expired=" + expired + ", gender=" + gender
				+ "]";
	}



	public Account(String username, String password, String fullname, String email, String phone, Integer roleId,
			String imageUrl, String studentCode, Integer grade, String major, boolean expired, boolean gender) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.imageUrl = imageUrl;
		this.studentCode = studentCode;
		this.grade = grade;
		this.major = major;
		this.expired = expired;
		this.gender = gender;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	// true - female  <->  false - male
	private boolean gender; 
	public String getUsername() {
		return username;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}	
	
	
}

package com.pop.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private String userId;
    private String username;
	private String fullname;
    private String email;
	private String phone;	
	private Date dob;
	public User() {
		
	}
	public User(String userId, String username, String fullname, String email, String phone, Date dob) {
		super();
		this.userId = userId;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + userId + ", username=" + username + ", fullname=" + fullname + ", email=" + email
				+ ", phone=" + phone + ", dob=" + dob + "]";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
}
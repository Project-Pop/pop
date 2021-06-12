package com.pop.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	private int user_id;
	@NotNull
	@Size(min=3, max = 25)
    private String username;
	@Size(min = 3, max = 50)
	private String fullname;
	@Size(max = 100)
    private String email;
	@NotNull
	private String phone;	
	private Date dob;
	
	public User(int user_id, @NotNull @Size(min = 3, max = 25) String username,
			@Size(min = 3, max = 50) String fullname, @Size(max = 100) String email, @NotNull String phone, Date dob) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
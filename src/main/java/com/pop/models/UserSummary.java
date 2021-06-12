package com.pop.models;

public class UserSummary {
    private String userId;
    private String name;
    private String username;
    private String avatar;
    	
    public UserSummary(String userId, String name, String username, String avatar) {
    	this.userId = userId;
    	this.name = name;
    	this.username = username;
    	this.avatar = avatar;
    }
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
}

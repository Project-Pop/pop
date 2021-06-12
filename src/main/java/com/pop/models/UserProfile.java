package com.pop.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfile {
	private int user_id;
	@NotNull
	@Size(min=3, max = 25)
    private String username;
	int views;
	int reacts;
	int popScore;
	User user;
	
	public UserProfile(int user_id, @NotNull @Size(min = 3, max = 25) String username, int views, int reacts,
			int popScore, User user) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.views = views;
		this.reacts = reacts;
		this.popScore = popScore;
		this.user = user;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getReacts() {
		return reacts;
	}
	public void setReacts(int reacts) {
		this.reacts = reacts;
	}
	public int getPopScore() {
		return popScore;
	}
	public void setPopScore(int popScore) {
		this.popScore = popScore;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}

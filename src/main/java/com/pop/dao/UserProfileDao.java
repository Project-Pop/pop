package com.pop.dao;

import java.util.List;

import com.pop.models.UserProfile;

public interface UserProfileDao {
	public List<UserProfile> getAll();
	public UserProfile get(int userId);
	public boolean exists(String phoneNo);
	public UserProfile findByEmailAddress(String emailAddress);
	public UserProfile findByUsername(String username);
	public void updateUserProfile(UserProfile user);
	public void increaseViews();
	public void increaseReacts();
	public void increasePopScore();
	public void setPopScore();
	public void decreasePopScore();
}

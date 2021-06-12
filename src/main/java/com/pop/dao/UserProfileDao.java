package com.pop.dao;

import java.util.List;

import com.pop.models.UserProfile;

public interface UserProfileDao {
	public List<UserProfile> getAll();
	public boolean exists(String phoneNo);
	public UserProfile getUserProfileByUsername(String username);
	// unimplemented
	public void updateUserProfile(UserProfile user);
	public void updateImageUrl(String imageUrl, String username);
	public void increaseViews(String username);
	public void increaseReacts(String username);
	public void increasePopScore(String username);
	public void setPopScore(String username);
	public void decreasePopScore(String username);
}

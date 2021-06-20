package com.pop.dao;

import java.util.List;

import com.pop.models.UserProfile;

public interface UserProfileDao {
	public List<UserProfile> getAll();
	public UserProfile getUserProfileByUsername(String username);
	// unimplemented
	public String getProfileImageUrl(String username);
	public void updateUserProfile(UserProfile user);
	public void updateImageUrl(String imageUrl, String username);
	public void updateBio(String username, String bio);
	public void increaseViews(String username);
	public void increaseReacts(String username);
	public void increasePopScore(String username);
	public void setPopScore(String username, int popScore);
	public void decreasePopScore(String username);
	public void createProfile(String username);
	public void followUser(String username, String followerUsername);
	public void unFollowUser(String username, String followerUsername);
}

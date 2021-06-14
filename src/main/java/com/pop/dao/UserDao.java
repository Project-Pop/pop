package com.pop.dao;

import java.util.List;

import com.pop.models.User;


public interface UserDao { 
	public List<User> getAll();
	public User getUserByUserId(String userId);
	public boolean exists(String phoneNo);
	public boolean isUserNameAvailable(String username);
	public String getUsernameByUserId(String username);

	public User getUserByEmailAddress(String emailAddress);
	public User getUserByUsername(String userId);
	
	// Update User only email or dob can be udpated
	public void updateUser(User user);
	
}

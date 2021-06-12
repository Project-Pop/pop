package com.pop.dao;

import java.util.List;

import com.pop.models.User;

public interface UserDao {
	public List<User> getAll();
	public User get(int userId);
	public boolean exists(String phoneNo);
	public User findByEmailAddress(String emailAddress);
	public User findByUsername(String username);
	
	// Update User only email or dob can be udpated
	public void updateUser(User user);
	
}

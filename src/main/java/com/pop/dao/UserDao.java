package com.pop.dao;

import java.util.Date;
import java.util.List;

import com.pop.dto.SignUpUserDto;
import com.pop.models.Notification;
import com.pop.models.User;


public interface UserDao { 
	public List<User> getAll();
	public User getUserByUserId(String userId);
	public boolean exists(String phoneNo);
	public boolean isUserNameAvailable(String username);
	public String getUsernameByUserId(String username);
	public void saveUser(User u);
	public User getUserByEmailAddress(String emailAddress);
	public User getUserByUsername(String userId);
	
	// Update User only email or dob can be udpated
	public void updateUser(User user);

//	TODO: implement below methods;
//	public void saveNotification(Notification notification);
//	public  void updateNotification(String notificationId,String title, boolean opened, Date timestamp);
//	public List<Notification> getNotifications(String username);
}

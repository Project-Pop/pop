package com.pop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pop.dao.UserDao;
import com.pop.models.User;

@RestController
public class SimpleController {

	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	public SimpleController(UserDaoImp userDao) {
//		this.userDao = userDao;
//	}
	
	
	
	
    @GetMapping("/api/hello")
    public User getResp(){
    	return null;
//    	return userDao.getUserByUserId("1");
//        return "Hey authenticated request";
    }
}

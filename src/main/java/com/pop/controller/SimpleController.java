package com.pop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pop.dao.UserDaoImpl;
import com.pop.dao.UserProfileDaoImpl;
import com.pop.models.User;

@RestController
public class SimpleController {
//
	@Autowired
	private UserDaoImpl userDao;
	
	@Autowired
	private JdbcTemplate jt;
	
	@Autowired
	private UserProfileDaoImpl userProfileDao;

	
    @GetMapping("/api/hello")
    public String getResp(User user){
//    	String sql = "INSERT INTO Users values('2', 'addyUrDaddy', 'Adarsh Gupta', 'adarshgupta.cse18@iitbhu.ac.in', '+918384010121', NULL)";
//    	jt.update(sql);
    	System.out.println(user.toString());
    	userDao.updateUser(user);
    	return "Hola";

    }
    @PostMapping("/api/hello")
    public String getRespPost(@RequestBody User user){
//    	String sql = "INSERT INTO Users values('2', 'addyUrDaddy', 'Adarsh Gupta', 'adarshgupta.cse18@iitbhu.ac.in', '+918384010121', NULL)";
//    	jt.update(sql);
    	System.out.println(user.toString());
    	userDao.updateUser(user);
    	return "Hola";

    }

}

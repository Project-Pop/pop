package com.pop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.pop.models.User;

@Transactional
@Controller
public abstract class UserDaoImp implements UserDao{

	final private JdbcTemplate jt;
	
	@Autowired
	public UserDaoImp(JdbcTemplate jt) {
		super();
		this.jt = jt;
	}


	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUserByUserId(String userId) {
		 try {
	            String sql = "SELECT * FROM Users WHERE userId = ?";
	            return (User) jt.queryForObject(sql,
	                    new BeanPropertyRowMapper<>(User.class), 
	                    userId);
        } catch (EmptyResultDataAccessException e) {
	            return null;
        }
	}


	@Override
	public boolean exists(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public User getUserByEmailAddress(String emailAddress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	
	
}

package com.pop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pop.dao.rowmappers.UserRowMapper;
import com.pop.models.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	final private JdbcTemplate jt;
	
	@Autowired
	public UserDaoImpl(JdbcTemplate jt) {
		super();
		this.jt = jt;
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM Users";
		return jt.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}


	@Override
	public User getUserByUserId(String userId) {
		 try {
	            String sql = "SELECT * FROM Users WHERE userId = ?";
	            return  jt.queryForObject(sql,
	                    new BeanPropertyRowMapper<User>(User.class), 
	                    userId);
        } catch (EmptyResultDataAccessException e) {
	            return null;
        }
	}


	@Override
	public boolean exists(String phoneNo) {
		String sql = "SELECT count(*) FROM Users WHERE phoneNo = ? ";
		int count = jt.queryForObject(sql, new Object[] { phoneNo }, Integer.class);
		return (count > 0);
	}
	
	
	

	@Override
	public boolean isUserNameAvailable(String username) {
		String sql = "SELECT count(*) FROM Users WHERE username = ? ";
		int count = jt.queryForObject(sql, new Object[] { username }, Integer.class);
		return (count == 0);
	}

	@Override
	public User getUserByEmailAddress(String emailAddress) {
		 try {
	            String sql = "SELECT * FROM Users WHERE email = ?";
	            return  jt.queryForObject(sql,
	                    new BeanPropertyRowMapper<User>(User.class), 
	                    emailAddress);
	     } catch (EmptyResultDataAccessException e) {
		            return null;
	     }
	}


	@Override
	public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Users   as U INNER JOIN UserProfile as UP ON U.username = UP.username where U.username = ?";      
        return  jt.queryForObject(sql,
                new UserRowMapper(), 
                username);
	     
	}


	@Override
	public void updateUser(User user) {
		String sql = "UPDATE Users SET fullname = ?, email = ? WHERE userId = ?";
		jt.update(sql, user.getFullname(), user.getEmail(), user.getUserId());
	}

	
	@Override
	public String getUsernameByUserId(String userId) {
		 String sql = "SELECT username FROM Users WHERE userId = ?";
         return  jt.queryForObject(sql,
                 new BeanPropertyRowMapper<String>(String.class), userId);
	}

	@Override
	public void saveUser(User u) {
//		System.out.println(u);
		String sql = "INSERT INTO Users (userId, username, fullname, email, phone, dob) values(?,?,?,?,?,?)";
		jt.update(sql, u.getUserId(), u.getUsername(), u.getFullname(), u.getEmail(), u.getPhone(),u.getDob());
	}

	
}

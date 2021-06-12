package com.pop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pop.models.User;
import com.pop.models.UserProfile;

@Transactional
@Repository
public class UserProfileDaoImpl implements UserProfileDao{
	
	final private JdbcTemplate jt;
	
	@Autowired
	public UserProfileDaoImpl(JdbcTemplate jt) {
		this.jt = jt;
	}
		
	@Override
	public List<UserProfile> getAll() {
		String sql = "SELECT * FROM UserProfile";
		return jt.query(sql, new BeanPropertyRowMapper<UserProfile>(UserProfile.class));	
	}

	

	@Override
	public UserProfile getUserProfileByUsername(String username) {
		 String sql = "SELECT * FROM UserProfile WHERE username = ?";
         return  jt.queryForObject(sql,
                 new BeanPropertyRowMapper<UserProfile>(UserProfile.class), 
                 username);
	}
	
	@Override
	public void updateImageUrl(String imageUrl, String username) {
		String sql = "UPDATE UserProfile SET imageUrl = ? where username = ?";
		jt.update(sql, imageUrl, username);
	}

	
	@Override
	public void updateUserProfile(UserProfile user) {
	}

	@Override
	public boolean exists(String phoneNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void increaseViews(String username) {
		String sql = "UPDATE UserProfile SET views = views + 1 where username = ?";
		jt.update(sql, username);
	}

	@Override
	public void increaseReacts(String username) {
		String sql = "UPDATE UserProfile SET reacts = reacts + 1 where username = ?";
		jt.update(sql, username);
	}

	@Override
	public void increasePopScore(String username) {
		String sql = "UPDATE UserProfile SET popScore = popScore + 1 where username = ?";
		jt.update(sql, username);		
	}

	@Override
	public void setPopScore(String username, int popScore) {
		String sql = "UPDATE UserProfile SET popScore = ? where username = ?";
		jt.update(sql, username);				
	}

	@Override
	public void decreasePopScore(String username) {
		String sql = "UPDATE UserProfile SET popScore = popScore - 1 where username = ?";
		jt.update(sql, username);			
	}


	


}

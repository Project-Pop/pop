package com.pop.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.pop.models.User;
import com.pop.models.UserProfile;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);		
		UserProfile userProfile =  (new BeanPropertyRowMapper<>(UserProfile.class)).mapRow(rs, rowNum);
		user.setUserProfile(userProfile);
		return user;
	}
	

}
	
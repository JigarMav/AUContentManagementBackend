package com.example.course.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.course.models.User;
import org.springframework.jdbc.core.RowMapper;



public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserID(rs.getInt("userID"));
		user.setUserName(rs.getString("userName"));
		user.setEmail(rs.getString("email"));
		user.setUserLocation(rs.getString("userLocation"));
		return user;
	}
}

package com.example.course.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.course.LoggerConfig;
import com.example.course.Queries.Queries;
import com.example.course.dao.UserDao;
import com.example.course.models.User;
import com.example.course.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		users = jdbcTemplate.query(Queries.GET_ALL_USERS, new UserRowMapper());
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		User u = null;
		try {
			return u = jdbcTemplate.queryForObject(Queries.GET_USERS_BY_EMAIL, new UserRowMapper(), email);
		}
		catch(DataAccessException e) {
			LoggerConfig.LOGGER.error("User Not Valid! => " + email);
		}
		return u;
	}
	@Override
	public User getUserById(int id) {
		String query = "SELECT * FROM users WHERE userID = ?";
		User u = null;
		try {
			return u = jdbcTemplate.queryForObject(Queries.GET_USER_BY_ID, new UserRowMapper(), id);
		}
		catch(DataAccessException e) {
			LoggerConfig.LOGGER.error("User Not Valid! => " + id);
		}
		return u;
	}

	@Override
	public void addUser(User user) {
		System.out.println("add user called for "+user.getUserName());
		String query = "INSERT INTO users (userName,email,userLocation) VALUES (?,?,?)";
		int a = jdbcTemplate.update(Queries.CREATE_USER, user.getUserName(),user.getEmail(),user.getUserLocation());
	}

	@Override
	public void deleteUser(int id) {
		String query = "DELETE FROM users WHERE userID = ?";
		jdbcTemplate.update(Queries.DELETE_USER, id);
		
	}
	
	
}

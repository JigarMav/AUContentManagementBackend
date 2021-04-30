package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.models.User;
import com.example.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDaoImpl userdao;
	
	@Override
	public List<User> getAllUsers() {
		return userdao.getAllUsers();
	}

	@Override
	public User getUserByEmail(String email) {
		return userdao.getUserByEmail(email);
	}

	@Override
	public void addUser(User user) {

		userdao.addUser(user);
	}

	@Override
	public void deleteUser(int id) {
		userdao.deleteUser(id);
		
	}
	 
}

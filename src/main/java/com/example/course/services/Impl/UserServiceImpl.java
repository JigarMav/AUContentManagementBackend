package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.dao.UserDao;
import com.example.course.models.User;
import com.example.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userdao;
	
	@Override
	public List<User> getAllUsers() {
		return userdao.getAllUsers();
	}

	@Override
	public User getUserByEmail(String email) {
		return userdao.getUserByEmail(email);
	}

	@Override
	public User getUserById(int id) {
		System.out.println("getuser"+id);
		return userdao.getUserById(id);
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

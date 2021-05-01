package com.example.course.dao;


import com.example.course.models.User;

import java.util.List;



public interface UserDao {

	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
	
	public void addUser(User user);
	public User getUserById(int id);
	/*
	public void updateUser(User user); */
	
	public void deleteUser(int id); 
}

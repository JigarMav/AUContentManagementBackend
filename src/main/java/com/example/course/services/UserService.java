package com.example.course.services;

import com.example.course.models.User;

import java.util.List;



public interface UserService {

	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
	User getUserById(int id);
	public void addUser(User user);
	/*
	public void updateUser(User user); */
	
	public void deleteUser(int id); 
	
}

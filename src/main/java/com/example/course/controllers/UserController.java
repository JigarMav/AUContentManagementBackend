package com.example.course.controllers;


import java.net.http.HttpHeaders;
import java.util.List;

import com.example.course.LoggerConfig;
import com.example.course.models.User;
import com.example.course.services.Impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping(value= "/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping(value= "/{email}")
	public User getUserByEmail(@PathVariable("email") String email) {
		LoggerConfig.LOGGER.info("User Request LogIn ->" + email);
		return userService.getUserByEmail(email);
	}

	@PostMapping( path = "/login", consumes = "application/json")
	@ResponseBody
	public void  loginUser(@RequestBody User user) {
		LoggerConfig.LOGGER.info("User Controller Called-- Login User");

//		token = token.substring(0, 20);
//		System.out.println(token);
//		HttpHeaders responseHeaders = new HttpHeaders();
//		HttpStatus httpstatus= HttpStatus.OK;
		System.out.println();
//		User msg=new User(user);

		User u = userService.getUserByEmail(user.getEmail());
		if(u==null)
		{
			System.out.println("User not getbyEmail");
		}

		if(u != null ) {
			System.out.println("user already present");
		}else {
			userService.addUser(user);
			LoggerConfig.LOGGER.info("new user added");
		}

//		ResponseEntity responseEntity = new ResponseEntity(msg,responseHeaders,httpstatus);
//		return responseEntity;
	}


	/*
	@PostMapping("/add")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	} */
	
}

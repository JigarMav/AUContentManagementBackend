package com.example.course.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class User {

	private int userID;
	private String userName;
	private String email;
	private String userLocation;

	public User(int userID, String userName, String email, String userLocation) {
		this.userID = userID;
		this.userName = userName;
		this.email = email;
		this.userLocation = userLocation;
	}

	public User() {
	}

	public User(String userName, String email, String userLocation) {
		super();
		this.userName = userName;
		this.email = email;
		this.userLocation = userLocation;
	}



	
	
}

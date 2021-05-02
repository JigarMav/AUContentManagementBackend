package com.example.course.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trainer extends User {

	private int trainerID;
	private int courseID;


	public Trainer() {
		super();
	}

	public Trainer(int trainerID, int courseID, String feedback) {
		this.trainerID = trainerID;
		this.courseID = courseID;
	}



}
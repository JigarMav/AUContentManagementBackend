package com.example.course.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@NoArgsConstructor

public class TrainingMaterial {

	private int materialID;

	private int courseID;
	private int trainerID;
	private String trainerName;
	private String fileName;
	private String fileType;
	private byte[] file;
	private String active_flag;
	private String status;
	private Timestamp created_on;
	private Timestamp last_modified;



	
}

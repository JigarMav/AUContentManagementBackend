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
	private String fileName;
	private String fileType;
	private byte[] file;
	private String active_flag;
	private String status;
	private Timestamp last_modified;


	public TrainingMaterial(int courseID, int trainerID, String fileName, String fileType, byte[] file, String active_flag, String status, Timestamp last_modified) {
		this.courseID = courseID;
		this.trainerID = trainerID;
		this.fileName = fileName;
		this.fileType = fileType;
		this.file = file;
		this.active_flag = active_flag;
		this.status = status;
		this.last_modified = last_modified;
	}





		
	
}

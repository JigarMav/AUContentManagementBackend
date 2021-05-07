package com.example.course.models;

import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Course {

	private int courseID;
	private int creatorID;
	private String courseName;
	private String courseDesc;
	private String courseSkills;
	private String coursePrerequisites;
	private String courseLocation;
	private Timestamp last_modified;

//	public Course(int creatorID,String courseName, String courseDesc, String courseSkills,
//				  String coursePrerequisites, String courseLocation,
//				  Timestamp last_modified) {
//		this.creatorID = creatorID;
//		this.courseName = courseName;
//		this.courseDesc = courseDesc;
//		this.courseSkills = courseSkills;
//		this.coursePrerequisites = coursePrerequisites;
//		this.courseLocation = courseLocation;
//		this.last_modified = last_modified;
//	}
}

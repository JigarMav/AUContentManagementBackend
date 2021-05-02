package com.example.course.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

	private int courseID;
	private String courseName;
	private String courseDesc;
	private String courseSkills;
	private String coursePrerequisites;
	private String courseLocation;
	private Timestamp last_modified;

	public Course(String courseName, String courseDesc, String courseSkills,
				  String coursePrerequisites, String courseLocation,
				  Timestamp last_modified) {
		this.courseName = courseName;
		this.courseDesc = courseDesc;
		this.courseSkills = courseSkills;
		this.coursePrerequisites = coursePrerequisites;
		this.courseLocation = courseLocation;
		this.last_modified = last_modified;
	}
}

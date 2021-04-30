package com.example.course.services;

import com.example.course.models.Course;

import java.util.List;


public interface CourseService {

	public List<Course> getAllCourses();
	
	public Course getCourseByName(String name);
	
	public void addCourse(Course course);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(int id);
	
}

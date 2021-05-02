package com.example.course.services;

import com.example.course.models.Course;

import java.util.List;


public interface CourseService {

	List<Course> getAllCourses();
	List<Course> getCoursesByTrainer(int id);
	Course getCourseByName(String name);
	
	Course addCourse(Course course);
	
	void updateCourse(Course course);
	
	void deleteCourse(int id);
	
}

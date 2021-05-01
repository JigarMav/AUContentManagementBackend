package com.example.course.dao;

import com.example.course.models.Course;

import java.util.List;



public interface CourseDao {

	List<Course> getAllCourses();
	
	Course getCourseByName(String name);
	List<Course> getCoursesByTrainer(int id);
	void addCourse(Course course);
	
	void updateCourse(Course course);
	
	void deleteCourse(int id);
}

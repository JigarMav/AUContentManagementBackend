package com.example.course.dao;

import com.example.course.models.Course;

import java.util.List;



public interface CourseDao {

	List<Course> getAllCourses();
	
	Course getCourseByName(String name);



	Course getCourseById(int id);

	List<Course> getCoursesByTrainer(int id);

	List<Course> getCoursesByCreator(int id);

	List<Course> getCoursesBySubscription(int id);
	Course addCourse(Course course);
	
	void updateCourse(Course course);
	
	void deleteCourse(int id);
}

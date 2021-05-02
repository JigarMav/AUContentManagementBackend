package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.CourseDao;
import com.example.course.models.Course;
import com.example.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseServiceImpl implements CourseService {

	@Override
	public List<Course> getCoursesByTrainer(int id) {
		return this.coursedao.getCoursesByTrainer(id);
	}

	@Autowired
	CourseDao coursedao;
	
	@Override
	public List<Course> getAllCourses() {
		return coursedao.getAllCourses();
	}

	@Override
	public Course getCourseByName(String name) {
		return coursedao.getCourseByName(name);
	}

	@Override
	public Course addCourse(Course course) {
		return coursedao.addCourse(course);
		
	}

	@Override
	public void updateCourse(Course course) {
		coursedao.updateCourse(course);
		
	}

	@Override
	public void deleteCourse(int id) {
		coursedao.deleteCourse(id);
		
	}

}

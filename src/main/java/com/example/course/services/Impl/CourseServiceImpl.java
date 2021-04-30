package com.example.course.services.Impl;

import java.util.List;

import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.models.Course;
import com.example.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseDaoImpl coursedao;
	
	@Override
	public List<Course> getAllCourses() {
		return coursedao.getAllCourses();
	}

	@Override
	public Course getCourseByName(String name) {
		return coursedao.getCourseByName(name);
	}

	@Override
	public void addCourse(Course course) {
		coursedao.addCourse(course);
		
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

package com.example.course.dao.Impl;

import java.util.List;

import com.example.course.LoggerConfig;
import com.example.course.dao.CourseDao;
import com.example.course.models.Course;
import com.example.course.rowmapper.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Course> getAllCourses() {
		String query = "SELECT * FROM courses";
		
		LoggerConfig.LOGGER.info("Fetched All Courses.");
		return jdbcTemplate.query(query, new CourseRowMapper());
	}

	@Override
	public List<Course> getCoursesByTrainer(int id) {
		String query = "SELECT courses.courseID,creatorID,courseDesc,courseLocation, courses.courseName ,coursePrerequisites ,courseSkills, last_modified" +
				"   FROM courses" +
				"   left join trainers" +
				"   on  trainers.courseID= courses.courseID" +
				"   where trainerID=?";

		LoggerConfig.LOGGER.info("Fetched Courses for trainer "+id);
		return jdbcTemplate.query(query, new CourseRowMapper(),id);
	}

	@Override
	public List<Course> getCoursesByCreator(int id) {
		String query = "SELECT * FROM courses WHERE creatorID=?";

		LoggerConfig.LOGGER.info("Fetched Courses for creator "+id);
		return jdbcTemplate.query(query, new CourseRowMapper(),id);
	}

	@Override
	public List<Course> getCoursesBySubscription(int id) {
		String query = "select courses.courseID,creatorID, courseDesc,courseLocation, courses.courseName ,coursePrerequisites ,courseSkills, last_modified from courses\n" +
				"left join subscription s on courses.courseID = s.courseID\n" +
				"where userID=?";

		LoggerConfig.LOGGER.info("Fetched Courses for subscribed user "+id);
		return jdbcTemplate.query(query, new CourseRowMapper(),id);
	}

	@Override
	public Course getCourseByName(String name) {
		LoggerConfig.LOGGER.info("Get Course  -> " + name);
		String query = "SELECT * FROM courses WHERE courseName = ? limit 1";
		return jdbcTemplate.queryForObject(query, new CourseRowMapper(), name); 
	}

	@Override
	public Course addCourse(Course course) {
		String query = "INSERT INTO courses (creatorID,courseName, courseDesc, courseSkills,coursePrerequisites,courseLocation,last_modified)" +
						" VALUES (?,?,?,?,?,?,NOW())";
		jdbcTemplate.update(query,course.getCourseID(), course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation());

		LoggerConfig.LOGGER.info("New Course Added -> " + course.getCourseName());

		return this.getCourseByName(course.getCourseName());
	}

	@Override
	public void updateCourse(Course course) {
		int cid = course.getCourseID();
		System.out.println("update for id "+cid);
		String query = "UPDATE courses SET courseName = ?, courseDesc = ?, courseSkills = ?,coursePrerequisites = ?,courseLocation = ?,last_modified=NOW()" +
						" WHERE courseID = ?";
		jdbcTemplate.update(query, course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
							course.getCoursePrerequisites(),course.getCourseLocation(), course.getCourseID());
		
		LoggerConfig.LOGGER.info("Course Updated -> " + course.getCourseName());
	}

	@Override
	public void deleteCourse(int id) {
		String query = "DELETE FROM courses WHERE courseID = ?";
		jdbcTemplate.update(query, id);
		
		LoggerConfig.LOGGER.info("Course Deleted -> " + id);
		
	}

}

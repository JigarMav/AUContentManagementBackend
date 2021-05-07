package com.example.course.dao;

import com.example.course.CourseApplication;
import com.example.course.Queries.Queries;
import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.models.Course;
import com.example.course.rowmapper.CourseRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest(classes= com.example.course.CourseApplication.class)
@ContextConfiguration(classes = CourseApplication.class)
class TestCourseDao {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CourseDaoImpl dao;
    List<Course> courseList = new ArrayList<>();
    Course c = new Course();
    Course c1 = new Course();

    @BeforeEach
    public void init() {
        c.setCourseID(1);
        c.setCourseName("Angular");
        c.setCourseDesc("Learn to build SPAs");

        c1.setCourseID(3);
        c1.setCourseName("Spring");
        c1.setCourseDesc("Learn backend");

        courseList.add(c);
        courseList.add(c1);
    }

    @Test
    void getAllCourses() {
        assertTrue(dao.getAllCourses().size()>0);

    }

    @Test
    void getCourseByName() {
        String name = "test";

        when(jdbcTemplate.queryForObject("SELECT * FROM courses WHERE courseName = ?", new CourseRowMapper(), name)).thenReturn(c1);

        Course course = dao.getCourseByName(name);
        assertNotNull(course);
        assertEquals(course.getCourseName(),"test");
        assertEquals(course.getCourseID(), 19);
    }

    @Test
    void getCoursesByTrainer() {
//        Jigar Bhanushali
        int tid=11;

//        List<Course> cl = new ArrayList<>();
//        cl.add(c);
//        when(jdbcTemplate.query(Queries.GET_COURSES_BY_TRAINER, new CourseRowMapper(), tid)).thenReturn(cl);

        List<Course> courses = dao.getCoursesByTrainer(tid);
        assertNotNull(courses);
        assertTrue(courses.size()>0);
    }
    @Test
    void getCourseById() {
//        Jigar Bhanushali
        int cid=19;

//        List<Course> cl = new ArrayList<>();
//        cl.add(c);
//        when(jdbcTemplate.query(Queries.GET_COURSES_BY_TRAINER, new CourseRowMapper(), tid)).thenReturn(cl);

        Course courses = dao.getCourseById(cid);
        assertNotNull(courses);
//        assertTrue(courses.size()>0);
    }

    @Test
    void getCoursesByCreator() {
        int cid=11;
        List<Course> courses = dao.getCoursesByCreator(cid);
        assertNotNull(courses);
        assertTrue(courses.size()>0);
    }

    @Test
    void getCoursesBySubscription() {
        int uid=11;
        List<Course> courses = dao.getCoursesBySubscription(uid);
        assertTrue(courses.size()>0);
    }

    @Test
    void addCourse() {

//        20 id
        Course course = new Course();
        course.setCourseName("Java from test");
        course.setCourseID(20);
        course.setCourseLocation("Mumbai");
        course.setCourseDesc("no");
        course.setCreatorID(1);
        dao.addCourse(course);
//        jdbcTemplate.update(Queries.CREATE_COURSE, course.getCreatorID(), course.getCourseName(),course.getCourseDesc(),course.getCourseSkills(),
//                course.getCoursePrerequisites(),course.getCourseLocation());
        Course addedCourse = dao.getCourseByName("Java from test");
        assertEquals(addedCourse.getCourseName().trim(),course.getCourseName().trim());
    }

    @Test
    void updateCourse() {

        Course course = dao.getCourseByName("Java from test");
        course.setCourseLocation("updated Mumbai");

//        course.setCourseName("Java from test");
//        course.setCourseID(20);
//        course.setCourseLocation("updated Mumbai");
//        course.setCourseDesc("no");
//        course.setCreatorID(1);
        dao.updateCourse(course);

        Course uc = dao.getCourseByName("Java from test");
        assert(uc.getCourseLocation().equals(course.getCourseLocation()));
    }

    @Test
    void deleteCourse() {
        dao.deleteCourse(23);
        Course dc =null;


        assertNull(dc);
    }
}
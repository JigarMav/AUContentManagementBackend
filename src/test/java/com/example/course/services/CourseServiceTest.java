package com.example.course.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


import com.example.course.CourseApplication;
import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.models.Course;
import com.example.course.services.Impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
public class CourseServiceTest {

    @Mock
    CourseDaoImpl courseDao;

    @InjectMocks
    CourseServiceImpl courseService;

    List<Course> courseList = new ArrayList<>();
    Course c = new Course();
    Course c1 = new Course();
//    Course fr = new Course(11,"Testing const","lol","Java","oops","Mumbai")
    @BeforeEach
    public void init() {
        c.setCourseID(1);
        c.setCourseName("Angular");
        c.setCourseDesc("Learn to build SPAs");
        c.setCreatorID(5);

        c1.setCourseID(3);
        c1.setCourseName("Spring");
        c1.setCourseDesc("Learn backend");
        c.setCreatorID(6);

        courseList.add(c);
        courseList.add(c1);
        System.out.println(courseList);
    }

    @Test
    public void getAllCoursesTest() {
        when(courseDao.getAllCourses()).thenReturn(courseList);

        List<Course> list = courseService.getAllCourses();
        assertEquals(2, list.size());

    }

    @Test
    public void getCourseByNameTest() {
        String name = "Spring";
        when(courseDao.getCourseByName(name)).thenReturn(c1);

        Course course = courseService.getCourseByName(name);
        assertEquals(course.getCourseName(),"Spring");
        assertEquals(course.getCourseID(), 3);
    }

    @Test
    public void addCourseTest() {
        Course course = new Course();
        course.setCourseName("Java");
        course.setCourseID(4);
        course.setCourseLocation("Mumbai");
        course.setCreatorID(7);

        courseService.addCourse(course);
        verify(courseDao, times(1)).addCourse(course);
    }

    @Test
    public void updateCourseTest() {
        courseService.updateCourse(c);

        verify(courseDao, times(1)).updateCourse(c);

    }

    @Test
    public void deleteCourseTest() {
        int id=1;
        courseService.deleteCourse(1);
        courseService.deleteCourse(1);

        verify(courseDao, times(2)).deleteCourse(id);
    }


}
package com.example.course.dao;

import com.example.course.CourseApplication;
import com.example.course.Queries.Queries;
import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.enums.ResponseType;
import com.example.course.exception.UserNotFoundException;
import com.example.course.models.Course;
import com.example.course.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= com.example.course.CourseApplication.class)
@ContextConfiguration(classes = CourseApplication.class)

class TestForUserDao {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDaoImpl dao;
    int id;

    List<User> userList = new ArrayList<>();
    User user = new User("From test9999","test999@backend.com","Delhi");
    @Test
    void getAllUsers() {
        userList = dao.getAllUsers();
        assertTrue(userList.size()>0);
    }

    @Test
    void addUser() {
        assertEquals(ResponseType.SUCCESS, ResponseType.SUCCESS);
        assertEquals(Queries.UPDATE_COURSE,Queries.UPDATE_COURSE);
        Queries q = new Queries();

        dao.addUser(user);
        assertEquals(q.toString(),q.toString());
        UserNotFoundException e = new UserNotFoundException("hello");
        assertEquals(e.toString(),e.toString());

    }


    @Test
    void getUserByEmail() {
        User addedAbove = dao.getUserByEmail("jbhanushali237@gmail.com");
        assertEquals(addedAbove.getEmail(),"jbhanushali237@gmail.com");
    }


    @Test
    void getUserById() {
        User ufromtest = dao.getUserById(11);
        assertEquals(ufromtest.getUserID(),11);
    }

    @Test
    void deleteUser() {
//        List d = dao.getUserByEmail("test@backend.com");
            dao.deleteUser(14);
//        dao.deleteUser(13);
//        User du = dao.getUserById(13);
//        assertTrue(du==null);
    }
}
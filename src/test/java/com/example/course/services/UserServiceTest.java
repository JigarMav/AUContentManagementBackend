package com.example.course.services;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.exception.UserNotFoundException;
import com.example.course.models.User;
import com.example.course.services.Impl.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
public class UserServiceTest {

    @Mock
    UserDaoImpl userDao;

    @InjectMocks
    UserServiceImpl userService;

    User u = new User();
    User u1 = new User();
    User fr = new User("jigarss","jigartest@gmail.com","Bangalore");

    List<User> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        u.setUserName("Rajan");
        u.setEmail("rajan@gmail.com");

        u1.setUserName("Tanish");
        u1.setEmail("tanish12@gmail.com");
        list.add(u);
        list.add(u1);
    }

    @Test
    public void getAllUserTest() {
        when(userDao.getAllUsers()).thenReturn(list);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void getUserByEmailTest() {
        String email = "rajan@gmail.com";
        when(userDao.getUserByEmail(email)).thenReturn(u);

        User user = userService.getUserByEmail(email);
        assertEquals(user.getUserName(), "Rajan");
        assertEquals(u.toString(),u.toString());
    }

    @Test
    public void addUserTest() {
        userService.addUser(u);

        verify(userDao).addUser(u);
    }

    @Test
    public void deleteUserTest() {
        int id = 1;
        userService.deleteUser(id);

        verify(userDao).deleteUser(id);
    }

//    @Test void findUserByIdFail()
//    {
//        User fuser = userService.getUserByEmail("jbhanushali237@gmail.com");
//        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(99));
//        assertEquals("Grad by id "+99+" was not found", exception.getMessage());
//    }
}
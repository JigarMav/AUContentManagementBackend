package com.example.course.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.course.CourseApplication;
import com.example.course.exception.UserNotFoundException;
import com.example.course.models.User;
import com.example.course.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private UserServiceImpl service;

    User u1 = new User();
    User u2 = new User();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    public void init() {
        u1.setUserID(1);
        u1.setUserName("Rajan");
        u1.setEmail("rajan@gmail.com");

        u2.setUserID(2);
        u2.setUserName("Sahil");
        u2.setEmail("sahil@gmail.com");

        userList.add(u1);
        userList.add(u2);
    }

    @Test
    public void getAllUserTest() throws Exception {
        when(service.getAllUsers()).thenReturn(userList);

        mock.perform(get("/api/user/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        when(service.getUserByEmail("rajan@gmail.com")).thenReturn(u1);

        mock.perform(get("/api/user/{email}", "rajan@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userID").value(1));

    }

    @Test
    public void getUserIDTest() throws Exception {
        when(service.getUserById(11)).thenReturn(u1);
        mock.perform(get("/api/user/get/{id}", 11))
                .andDo(print())
                .andExpect(status().isOk());

    }
//    @Test
//    public void loginUserTest() throws Exception {
//
//        User me = service.getUserById(11);
//        User elsewala = new User("Jigar elseaa ","jjaa@gmail.com","Mumbai");
//        User notme = service.getUserById(12);
//        if(me==null)
//
////        when(service.loginUser("jbhanushali237@gmail.com")).thenReturn(me);
//
//        mock.perform(get("/api/user/login", me))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        mock.perform(get("/api/user/login", elsewala))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
package com.example.course.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.course.CourseApplication;
import com.example.course.models.Subscription;
import com.example.course.models.Trainer;
import com.example.course.models.User;
import com.example.course.services.Impl.SubscriptionServiceImpl;
import com.example.course.services.Impl.TrainerServiceImpl;
import com.example.course.services.Impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(classes = CourseApplication.class)
@SpringBootTest(classes= com.example.course.CourseApplication.class)
@AutoConfigureMockMvc
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    SubscriptionServiceImpl service;

    List<Subscription> subList = new ArrayList<>();
    Subscription s = new Subscription();
    Subscription s1 = new Subscription();

    @BeforeEach
    public void init() {
        s.setUserID(1);
        s.setCourseID(2);
        s.setEmail("jigar@yahoo.com");

        s1.setUserID(10);
        s1.setCourseID(11);
        s1.setEmail("");

        subList.add(s);
        subList.add(s1);
    }

    @Test
    public void getAllSubscriptionsTest() throws Exception {
        when(service.getAllSubscription()).thenReturn(subList);

        mock.perform(get("/api/subscription/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));

    }

    @Test
    public void getSubscriptionByCourseIDTest() throws Exception {

        List<Subscription> sl = new ArrayList<>();
        sl.add(s1);
        when(service.getAllEmailsForCourse(11)).thenReturn(sl);

        mock.perform(get("/api/subscription/all/{id}", 11))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userID").value(10));

    }

    @Test
    public void addSubscriptionTest() throws Exception {

        mock.perform(post("/api/subscription/add")
                .content(asJsonString(s1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteSubscriptionTest() throws Exception {
        mock.perform(delete("/api/subscription/delete/{tid}/{cid}",2,22)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}
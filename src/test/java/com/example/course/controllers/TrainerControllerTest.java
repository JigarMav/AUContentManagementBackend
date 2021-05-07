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
import com.example.course.models.Trainer;
import com.example.course.models.User;
import com.example.course.services.Impl.TrainerServiceImpl;
import com.example.course.services.Impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
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
public class TrainerControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    TrainerServiceImpl service;

    Trainer t = new Trainer();
    Trainer t1 = new Trainer();
    List<Trainer> trainerList = new ArrayList<>();

    @BeforeEach
    public void init() {
        t.setTrainerID(1);
        t.setCourseID(2);
        t.setUserName("TestJigar");
        t.setEmail("testJigar@gmail.com");
        t.setUserLocation("Mumbai");


        t1.setTrainerID(10);
        t1.setCourseID(11);
        t1.setUserName("test2jigar");
        t1.setEmail("test2jigar@gmail.com");
        t1.setUserLocation("Delhi");

        trainerList.add(t);
        trainerList.add(t1);
    }

    @Test
    public void getAllTrainerTest() throws Exception {
        when(service.getAllTrainers()).thenReturn(trainerList);

        mock.perform(get("/api/trainer/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));

    }

    @Test
    public void getTrainerByCourseId() throws Exception {
        List<Trainer> tl = new ArrayList<>();
        tl.add(t1);
        when(service.getTrainerByCourseID(11)).thenReturn(tl);

        mock.perform(get("/api/trainer/{id}", 11))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].trainerID").value(10));

    }
    @Test
    public void getTrainerById() throws Exception {


        mock.perform(get("/api/trainer/select/{id}", 11))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void addTrainerTest() throws Exception {
        mock.perform(post("/api/trainer/add")
                .content(asJsonString(t1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void addTrainerAfterCourse() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tid",5);
        jsonObject.put("cid",24);

        mock.perform(post("/api/trainer/add/afterCourse")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void deleteTrainer() throws Exception {
        mock.perform(delete("/api/trainer/delete/{tid}/{cid}",2,22)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
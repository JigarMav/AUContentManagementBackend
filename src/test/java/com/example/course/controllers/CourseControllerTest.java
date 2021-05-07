package com.example.course.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.example.course.CourseApplication;
import com.example.course.models.Course;
import com.example.course.services.Impl.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private CourseServiceImpl service;

    List<Course> list = new ArrayList<>();
    Course c1 = new Course();
    Course c2 = new Course();

    @BeforeEach
    public void init() {
        c1.setCourseID(1);
        c1.setCourseName("Angular");
        c1.setCourseLocation("Mumbai");
        c1.setCreatorID(2);


        c2.setCourseID(2);
        c2.setCourseName("Spring");
        c2.setCourseLocation("Bangalore");
        c2.setCreatorID(3);

        list.add(c1);
        list.add(c2);
    }

    @Test
    public void getAllCourseTest() throws Exception {
        when(service.getAllCourses()).thenReturn(list);

        mock.perform(get("/api/course/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(2)));


    }

    @Test
    public void getCourseByNameTest() throws Exception {
        when(service.getCourseByName("Angular")).thenReturn(c1);

        mock.perform(get("/api/course/{name}", "Angular"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseID").value(1));

    }

    @Test
    public void getCoursesByCreatorTest() throws Exception {
        List<Course> cList = new ArrayList<>();
        cList.add(c1);
        when(service.getCoursesByCreator(2)).thenReturn(cList);

        mock.perform(get("/api/course/creator/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseID").value(1));

    }

    @Test
    public void getCoursesByTrainerTest() throws Exception {
        List<Course> cList = new ArrayList<>();
        cList.add(c1);
        when(service.getCoursesByTrainer(12)).thenReturn(cList);

        mock.perform(get("/api/course/trainer/{id}", 12))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

    }
    @Test
    public void getCoursesBySubTest() throws Exception {
        List<Course> cList = new ArrayList<>();
        cList.add(c1);
        when(service.getCoursesBySubscription(11)).thenReturn(cList);

        mock.perform(get("/api/course/user/{id}", 11))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

    }

    @Test
    public void addCourseTest() throws Exception {

        mock.perform(post("/api/course/add")
                .content(asJsonString(c2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateCourseTest() throws Exception{
        c1.setCourseID(1);
        c1.setCourseName("Angular");
        c1.setCourseLocation("Delhi");
        mock.perform(put("/api/course/update")
                .content(asJsonString(c1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteCourseTest() throws Exception {
        mock.perform(delete("/api/course/delete/{id}",2)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
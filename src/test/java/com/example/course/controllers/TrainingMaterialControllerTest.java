package com.example.course.controllers;

import static org.mockito.Mockito.verify;
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
import com.example.course.models.TrainingMaterial;
import com.example.course.services.Impl.TrainingMaterialServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
@AutoConfigureMockMvc
public class TrainingMaterialControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    TrainingMaterialServiceImpl service;

    TrainingMaterial t1 = new TrainingMaterial();
    TrainingMaterial t2 = new TrainingMaterial();
    List<TrainingMaterial> list = new ArrayList<>();

    @BeforeEach
    public void init() {

        t1.setMaterialID(1);
        t1.setCourseID(2);
        t1.setFileName("file");
        t1.setFileType("txt");
        t1.setStatus("New");

        t2.setMaterialID(3);
        t2.setCourseID(4);
        t2.setFileName("file2");
        t2.setFileType("pdf");
        t2.setStatus("New");

        list.add(t1);
        list.add(t2);

    }

    @Test
    public void getAllMaterial() throws Exception {
        when(service.getAllMaterial()).thenReturn(list);

        mock.perform(get("/api/material/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }

    @Test
    public void getMaterialActive() throws Exception {
        when(service.getMaterialActive()).thenReturn(list);

        mock.perform(get("/api/material/active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("New"));
    }

    @Test
    public void getMaterialByCourseIdTest() throws Exception {
        list.clear();

        list.add(t2);
        when(service.getMaterialByCourseID(4)).thenReturn(list);

        mock.perform(get("/api/material/course/all/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].materialID").value(3))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    public void deleteMaterialTest() throws Exception {
        mock.perform(delete("/api/material/delete/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    public void getActiveMaterialByCourseID() throws Exception {
        mock.perform(get("/api/material/{id}", 4))
                .andExpect(status().isOk());
    }
    @Test
    public void getMaterialByTrainerID() throws Exception {
        mock.perform(get("/api/material/trainer/{id}", 11))
                .andExpect(status().isOk());
    }

}
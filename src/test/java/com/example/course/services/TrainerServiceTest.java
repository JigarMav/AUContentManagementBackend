package com.example.course.services;


import com.example.course.CourseApplication;
import com.example.course.dao.Impl.TrainerDaoImpl;
import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.models.Trainer;
import com.example.course.models.User;
import com.example.course.services.Impl.TrainerServiceImpl;
import com.example.course.services.Impl.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
public class TrainerServiceTest {

    @Mock
    TrainerDaoImpl dao;

    @InjectMocks
    TrainerServiceImpl service;

    List<Trainer> trainerList = new ArrayList<>();
    Trainer t = new Trainer();
    Trainer t1 = new Trainer();
    @BeforeEach
    public void init() {
        t.setTrainerID(1);
        t1.setCourseID(2);

        t1.setTrainerID(10);
        t1.setCourseID(11);
        trainerList.add(t);
        trainerList.add(t1);
    }

    @Test
    public void getAllTrainerTest() {
        when(dao.getAllTrainers()).thenReturn(trainerList);

        List<Trainer> list = service.getAllTrainers();
        assertEquals(list.size(), 2);
        assertEquals(t.toString(),t.toString());
    }

    @Test
    public void getTrainerByCourseId() {
        List<Trainer> tl = new ArrayList<Trainer>();
        tl.add(t1);

        when(dao.getTrainerByCourseID(11)).thenReturn(tl);

        List<Trainer> trainer = service.getTrainerByCourseID(11);

        assertEquals(trainer.get(0).getTrainerID(), 10);
    }

    @Test
    public void addTrainerTest() {
        service.addTrainer(t);

        verify(dao).addTrainer(t);
    }

    @Test
    public void deletetrainer() {
        service.deleteTrainer(10, 11);
        service.deleteTrainer(10, 11);

        verify(dao, times(2)).deleteTrainer(10, 11);
    }
}
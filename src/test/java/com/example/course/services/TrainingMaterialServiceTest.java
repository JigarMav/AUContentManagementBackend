package com.example.course.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.TrainingMaterialDaoImpl;
import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.models.TrainingMaterial;
import com.example.course.models.User;
import com.example.course.services.Impl.TrainingMaterialServiceImpl;
import com.example.course.services.Impl.UserServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest(classes= com.example.course.CourseApplication.class)
@ContextConfiguration(classes = CourseApplication.class)

public class TrainingMaterialServiceTest {

    @Mock
    TrainingMaterialDaoImpl dao;

    @InjectMocks
    TrainingMaterialServiceImpl service;

    TrainingMaterial t1 = new TrainingMaterial();
    TrainingMaterial t2 = new TrainingMaterial();
    List<TrainingMaterial> list = new ArrayList<>();

    @BeforeEach
    public void init() {
        t1.setCourseID(1);
        t1.setFileName("file1");
        t1.setFileType("txt");
        t1.setStatus("New");
        t1.setTrainerID(1);
        t1.setTrainerName("Jigar");

        t2.setCourseID(2);
        t2.setFileName("file2");
        t2.setFileType("pdf");
        t2.setStatus("New");
        t2.setTrainerID(2);
        t2.setTrainerName("TestJigar");

        list.add(t1);
        list.add(t2);
    }

    @Test
    public void getAllTrainingMaterialTest() {
        when(dao.getAllMaterial()).thenReturn(list);

        List<TrainingMaterial> materials = service.getAllMaterial();
        assertEquals(materials.size(), 2);
        assertEquals(materials.get(1).getCourseID(), 2);
        assertEquals(t1.toString(),t1.toString());
    }

    @Test
    public void getMaterialByCourseID() {
        int id = 1;
        when(dao.getMaterialByCourseID(id)).thenReturn(list);

        List<TrainingMaterial> materials = service.getMaterialByCourseID(id);
        assertEquals(materials.size(), 2);
        assertEquals(materials.get(0).getCourseID(), 1);

    }

    @Test
    public void deleteMaterialTest() {
        int id = 1;
        service.deleteMaterial(id);
        service.deleteMaterial(id);

        verify(dao, times(2)).deleteMaterial(id);
    }

    @Test
    public void getMaterialActive() {
        when(dao.getMaterialActive()).thenReturn(list);

        List<TrainingMaterial> actList = service.getMaterialActive();
        assertEquals(actList.size(), 2);
        assertEquals(actList.get(1).getFileName(), "file2");
    }

    @Test
    public void addMaterialTest() {
        TrainingMaterial tm = new TrainingMaterial();
        MultipartFile file = null;
        int courseId = 1;
        int trainerId = 2;
        String tname = "Jigar";

        tm.setFile(null);
        tm.setCourseID(courseId);
        tm.setTrainerID(trainerId);
        tm.setTrainerName(tname);



        service.addMaterial(file, courseId,trainerId,tname);
        verify(dao).addMaterial(file, courseId,trainerId,tname);
    }


}
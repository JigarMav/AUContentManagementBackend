package com.example.course.dao;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.dao.Impl.TrainingMaterialDaoImpl;
import com.example.course.models.Course;
import com.example.course.models.TrainingMaterial;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ContextConfiguration(classes = CourseApplication.class)

@SpringBootTest(classes= com.example.course.CourseApplication.class)
class TrainingMaterialDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TrainingMaterialDaoImpl dao;

    List<TrainingMaterial> tlist = new ArrayList<>();
    TrainingMaterial tm = new TrainingMaterial();

    @Test
    void getAllMaterial() {
        tlist = dao.getAllMaterial();
        assertTrue(tlist.size()>0);
        tlist.clear();

    }

    @Test
    void getMaterialActive() {
        int len = dao.getAllMaterial().size();
        if(len>0)
        {
            tlist = dao.getMaterialActive();
            assertTrue(tlist.size()>0);
        }
        else{
            assertEquals(0, tlist.size());
        }

        tlist.clear();
    }

    @Test
    void getMaterialByCourseID() {
//        dao.addMaterial(null,15,22,"For test");

        tlist = dao.getMaterialByCourseID(99);
        assertEquals(0,
                tlist.size());
        tlist.clear();

    }

    @Test
    void getActiveMaterialByCourseID() {
        tlist = dao.getActiveMaterialByCourseID(2);
        assertTrue(tlist.size()>0);
        tlist.clear();
//        String status = tlist.get(0).getStatus();
//        assertSame("New", status);

    }

    @Test
    void getMaterialByTrainerID() {
        tlist = dao.getMaterialByTrainerID(11);
        assertTrue(tlist.size()>0);
    }

    @Test
    void deleteMaterial() {

         dao.deleteMaterial(7);
//        tlist = dao.getAllMaterial();

    }
    @Test
    void addMaterial() {

        dao.addMaterial(null,2,11,"Jigar");
//        tlist = dao.getAllMaterial();

    }
}
package com.example.course.dao;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.TrainerDaoImpl;
import com.example.course.models.Trainer;
import com.example.course.models.User;
import com.example.course.rowmapper.TrainerRowMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes= com.example.course.CourseApplication.class)
@ContextConfiguration(classes = CourseApplication.class)

public class TrainerDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TrainerDaoImpl dao;

    List<Trainer> trainerList = new ArrayList<>();
    Trainer t = new Trainer();
    Trainer t1 = new Trainer();

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
    public void addTrainerTest() {
        dao.addTrainer(t);
        String query = "INSERT INTO trainers(trainerID, courseID) VALUES (?,?)";
        verify(jdbcTemplate).update(query, t.getTrainerID(),t.getCourseID());

    }
//    @Test
//    public void getTrainerByIdTest()
//    {
//        String query = "SELECT * FROM users JOIN trainers ON users.userID = trainers.trainerID where trainers.trainerID= ? limit 1";
//
//        when(jdbcTemplate.queryForObject(query, new TrainerRowMapper(), 1)).thenReturn(t);
//        int id = 1;
//        int resp = dao.getTrainerById(id);
//
//
//        assertEquals(t.getTrainerID(), resp);
//    }

    @Test
    public void deletetrainer() {
        dao.deleteTrainer(10, 11);
        dao.deleteTrainer(10, 11);
        String query = "DELETE FROM trainers WHERE trainerID = ? AND courseID = ?";
        verify(jdbcTemplate, times(2)).update(query, 10, 11);
    }
    @Test
    public void getAllTrainers() {
        List<Trainer> uu= dao.getAllTrainers();
//        trainerList =
        assertTrue(uu.size()==0);
    }
    @Test
    public void getTrainerById() {
        int id = dao.getTrainerById(11);
        assertTrue(id!=0);
    }
    @Test
    public void getTrainerByCourseID() {
        trainerList = dao.getTrainerByCourseID(2);
        assertTrue(trainerList.size()==0);
    }
    @Test
    public void addTrainerAfterCourse() {
        trainerList.add(t);
        dao.addTrainerAfterCourse(12,19);
        assertTrue(trainerList.size()>0);
    }


}
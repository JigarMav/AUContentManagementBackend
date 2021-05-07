package com.example.course.dao;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.CourseDaoImpl;
import com.example.course.models.Subscription;
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
class TestForSubscriptionDao {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SubscriptionDao dao;

    Subscription sub = new Subscription(12,2,"codingdreaming1@gmail.com");
    List<Subscription> subList = new ArrayList<Subscription>();

    @Test
    void getAllSubscription() {
        assertTrue(dao.getAllSubscription().size()>0);
    }

    @Test
    void getAllEmailsForCourse() {
        subList = dao.getAllEmailsForCourse(18);
        assertNotNull(subList);
        subList.clear();
    }

    @Test
    void addSubscription() {
        subList = dao.getAllSubscription();
        dao.addSubscription(sub);
//        subList.clear();
        List<Subscription> newSublist = dao.getAllSubscription();

        assertTrue(subList.size()<newSublist.size());
        subList.clear();
    }

    @Test
    void deleteSubscription() {
        subList = dao.getAllSubscription();

        sub.setUserID(13);
        dao.addSubscription(sub);

        dao.deleteSubscription(13,2);

//        subList.clear();
        List<Subscription> newSublist = dao.getAllSubscription();

        assertEquals(subList.size(), newSublist.size());
        subList.clear();
    }
}
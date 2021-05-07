package com.example.course.services;

import com.example.course.CourseApplication;
import com.example.course.dao.Impl.SubscriptionDaoImpl;
import com.example.course.dao.Impl.UserDaoImpl;
import com.example.course.dao.SubscriptionDao;
import com.example.course.models.Subscription;
import com.example.course.models.Trainer;
import com.example.course.models.User;
import com.example.course.services.Impl.SubscriptionServiceImpl;
import com.example.course.services.Impl.SubscriptionServiceImpl;
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
class SubscriptionServiceTest {
    @Mock
    SubscriptionDao dao;

    @InjectMocks
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
    public void getAllSubscriptionTest()
    {
        when(dao.getAllSubscription()).thenReturn(subList);

        List<Subscription> list = service.getAllSubscription();
        assertEquals(list.size(), 2);
    }

    @Test
    public void getAllEmailsForCourseTest()
    {
        int cid = 2;
        List<Subscription> sl = new ArrayList<Subscription>();
        sl.add(s);

        when(dao.getAllEmailsForCourse(2)).thenReturn(sl);

        List<Subscription> list = service.getAllEmailsForCourse(cid);
        assertEquals(list.size(), 1);
    }
    @Test
    public void addSubscriptionTest()
    {
        service.addSubscription(s);

        verify(dao).addSubscription(s);
    }
    @Test
    public void deleteSubscriptionTest()
    {
        service.deleteSubscription(10, 11);
        service.deleteSubscription(10, 11);

        verify(dao, times(2)).deleteSubscription(10, 11);
    }

}
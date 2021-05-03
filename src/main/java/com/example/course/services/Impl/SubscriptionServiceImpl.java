package com.example.course.services.Impl;

import com.example.course.dao.SubscriptionDao;
import com.example.course.models.Subscription;
import com.example.course.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionDao subscriptionDao;

    @Override
    public List<Subscription> getAllSubscription() {
        return subscriptionDao.getAllSubscription();
    }

    @Override
    public List<Subscription> getAllEmailsForCourse(int cid) {
        return subscriptionDao.getAllEmailsForCourse(cid);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        subscriptionDao.addSubscription(subscription);
    }

    @Override
    public void deleteSubscription(int uid, int cid) {
        subscriptionDao.deleteSubscription(uid, cid);
    }
}

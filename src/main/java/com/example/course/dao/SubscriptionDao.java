package com.example.course.dao;

import com.example.course.models.Subscription;

import java.util.List;

public interface SubscriptionDao {
    List<Subscription> getAllSubscription();
    List<Subscription> getAllEmailsForCourse(int cid);
    void addSubscription(Subscription subscription);

    void deleteSubscription(int uid, int cid);

}

package com.example.course.dao;

import com.example.course.models.Subscription;

import java.util.List;

public interface SubscriptionDao {
    List<Subscription> getAllSubscription();
    List<Subscription> getAllEmailsForCourse(int cid);
    void addSubscription(int uid,int cid,String email);

    void deleteSubscription(int uid, int cid);

}

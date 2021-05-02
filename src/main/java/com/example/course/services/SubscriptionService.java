package com.example.course.services;

import com.example.course.models.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubscription();
    List<Subscription> getAllEmailsForCourse(int cid);
    void addSubscription(int uid,int cid,String email);

    void deleteSubscription(int uid, int cid);
}

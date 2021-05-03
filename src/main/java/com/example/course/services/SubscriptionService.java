package com.example.course.services;

import com.example.course.models.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubscription();
    List<Subscription> getAllEmailsForCourse(int cid);
    void addSubscription(Subscription subscription);

    void deleteSubscription(int uid, int cid);
}

package com.example.course.controllers;

import com.example.course.models.Subscription;
import com.example.course.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/subscription")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    
    @GetMapping(value= "/all")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscription();
    }

    @PostMapping(value= "/add")
    public void addSubscription(Subscription subscription) {
        subscriptionService.addSubscription(subscription);
    }

    @GetMapping(value= "/all/{id}")
    public List<Subscription> getSubscriptionByCourseID(@PathVariable("id") int id) {
        return subscriptionService.getAllEmailsForCourse(id);
    }



    @DeleteMapping(value= "/delete/{uid}/{cid}")
    public void deleteSubscription(@PathVariable("uid") int uid, @PathVariable("cid") int cid) {
        subscriptionService.deleteSubscription(uid,cid);
    }
    
}

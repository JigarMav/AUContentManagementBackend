package com.example.course.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription {
    private int userID;
    private int courseID;
    private String email;
}

package com.example.course.rowmapper;

import com.example.course.models.Subscription;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription mapRow(ResultSet rs, int i) throws SQLException {
        Subscription sub = new Subscription();
        
        sub.setUserID(rs.getInt("userID"));
        
        sub.setEmail(rs.getString("email"));
        
        sub.setCourseID(rs.getInt("courseID"));

        return sub;
    }
}

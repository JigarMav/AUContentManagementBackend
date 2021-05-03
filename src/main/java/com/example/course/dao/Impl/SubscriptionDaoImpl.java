package com.example.course.dao.Impl;

import com.example.course.LoggerConfig;
import com.example.course.dao.SubscriptionDao;
import com.example.course.models.Subscription;
import com.example.course.rowmapper.SubscriptionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Transactional
@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {

    private final JdbcTemplate jdbcTemplate;

    public SubscriptionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Subscription> getAllSubscription() {
        String GET_ALL_SUBSCRIPTION = "SELECT * FROM subscription";
        return jdbcTemplate.query(GET_ALL_SUBSCRIPTION, new SubscriptionRowMapper());
    }

    @Override
    public List<Subscription> getAllEmailsForCourse(int cid) {
        String GET_ALL_EMAIL_FOR_COURSE = "SELECT * FROM subscription WHERE courseID=?";
        return jdbcTemplate.query(GET_ALL_EMAIL_FOR_COURSE,new SubscriptionRowMapper(),cid);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        String ADD_SUBSCRIPTION = "INSERT INTO subscription(userID, courseID,email) VALUES (?,?,?)";
        int cid = subscription.getCourseID();
        int uid = subscription.getUserID();
        String email = subscription.getEmail();
        try {
            jdbcTemplate.update(ADD_SUBSCRIPTION, uid,cid,email);
            LoggerConfig.LOGGER.info("Subscription Added -> For user-> " + uid+" course-> "+cid);
        }
        catch(Exception e) {
            LoggerConfig.LOGGER.error("Error Adding subscription ->  For user-> " + uid+" course-> "+cid);
            e.getMessage();
        }
    }

    @Override
    public void deleteSubscription( int uid, int cid) {
        String query = "DELETE FROM subscription WHERE userID = ? AND courseID = ?";
        jdbcTemplate.update(query, uid, cid);

        LoggerConfig.LOGGER.info("Deleted subscription ->  For user-> " + uid+" course-> "+cid);
    }
}

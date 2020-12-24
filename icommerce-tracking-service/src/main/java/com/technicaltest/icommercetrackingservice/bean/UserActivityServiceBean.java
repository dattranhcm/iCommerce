package com.technicaltest.icommercetrackingservice.bean;

import com.technicaltest.icommercetrackingservice.dto.UserActivity;
import com.technicaltest.icommercetrackingservice.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityServiceBean {

    @Autowired
    private UserActivityRepository userActivityRepository;

    public void addTrackingActivity(UserActivity userActivity) {
        userActivityRepository.save(userActivity);
    }

    public List<UserActivity> findAll() {
        return userActivityRepository.findAll();
    }

    public List<UserActivity> findByUserName(String userName) {
        return userActivityRepository.findByUserName(userName);
    }

    public List<UserActivity> findByUserActivity(String activityName) {
        return userActivityRepository.findByActivityName(activityName);
    }

}

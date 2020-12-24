package com.technicaltest.icommercetrackingservice.repository;

import com.technicaltest.icommercetrackingservice.dto.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserActivityRepository extends MongoRepository<UserActivity, String> {
    public List<UserActivity> findAll();
    public List<UserActivity> findByUserName(String userName);
    public List<UserActivity> findByActivityName(String activityName);
}

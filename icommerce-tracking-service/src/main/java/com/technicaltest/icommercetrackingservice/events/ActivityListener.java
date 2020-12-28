package com.technicaltest.icommercetrackingservice.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommercetrackingservice.dto.UserActivity;
import com.technicaltest.icommercetrackingservice.dto.UserActivityMessage;
import com.technicaltest.icommercetrackingservice.repository.UserActivityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ActivityListener {
    private final Logger log = LoggerFactory.getLogger(ActivityListener.class);
    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "tracking-customer-activity")
    public void userActivityListener(String userActivityMessage , Acknowledgment acknowledgment) throws JsonProcessingException {
        log.info("ActivityListener CONSUME mapper:" + userActivityMessage);
        try {
            UserActivityMessage activityMessage = objectMapper.readValue(userActivityMessage, UserActivityMessage.class);
            UserActivity userActivity = new UserActivity();
            userActivity.setActionContent(activityMessage.getActionContent());
            userActivity.setActivityName(activityMessage.getActivityName());
            userActivity.setUserName(activityMessage.getUserName());
            userActivityRepository.save(userActivity);
            acknowledgment.acknowledge();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

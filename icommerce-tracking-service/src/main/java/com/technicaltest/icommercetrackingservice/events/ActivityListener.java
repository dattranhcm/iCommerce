package com.technicaltest.icommercetrackingservice.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivityListener {
    private final Logger log = LoggerFactory.getLogger(ActivityListener.class);

    @KafkaListener(topics = "tracking-customer-activity")
    public void userActivityListener(String activity, Acknowledgment acknowledgment) throws JsonProcessingException {
        log.info("ActivityListener CONSUME:" + activity);

        acknowledgment.acknowledge();
    }
}

package com.technicaltest.icommerceorderservice.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaListener {
    private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);


    @KafkaListener(topics = "order")
    public void order(String orderInfo, Acknowledgment acknowledgment) {
        log.info("Received order info, PROCESSING: " + orderInfo);
        acknowledgment.acknowledge();
    }
}

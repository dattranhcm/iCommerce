package com.technicaltest.icommerceorderservice.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.support.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderKafkaListener {
    private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "order")
    public void orderListener(String orderUUID, Acknowledgment acknowledgment) throws JsonProcessingException {
        log.info("OrderKafkaListener CONSUME:" + orderUUID);
        TOrder newInitOrder = orderRepository.findByUuid(UUID.fromString(orderUUID));
        newInitOrder.setStatus(OrderStatus.PROCESSING.name());
        orderRepository.save(newInitOrder);
        ObjectMapper mapper = new ObjectMapper();
        log.info("Received order info JSON, PROCESSING: " + mapper.writeValueAsString(newInitOrder));
        acknowledgment.acknowledge();
    }
}

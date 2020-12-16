package com.technicaltest.icommerceorderservice.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class OrderTestDataGenerator {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderTestDataGenerator(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void generateTestData() {
        orderRepository
                .save(new TOrder("Shopee12", new Date(), new Date()));
    }
}

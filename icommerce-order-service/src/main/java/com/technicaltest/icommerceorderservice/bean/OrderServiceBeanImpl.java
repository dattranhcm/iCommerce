package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.support.HTTPDataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceBeanImpl implements OrderServiceBean{
    private final Logger log = LoggerFactory.getLogger(OrderServiceBeanImpl.class);

    private OrderRepository orderRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderServiceBeanImpl(OrderRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        super();
        this.orderRepository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderResponse createOrder(TOrder order) {
//        order.setStatus(OrderStatus.INIT.name());
//        TOrder initOrder = orderRepository.save(order);
        log.info("OrderServiceBeanImpl INIT order");
        fireOrderCreatedEvent();
        return HTTPDataHelper.orderResponse(null);
    }

    @Override
    public List<TOrder> findOrderByUuid(UUID uuid) {
        return orderRepository.findByUuid(uuid);
    }

    private void fireOrderCreatedEvent() {
        kafkaTemplate.send("order", "order001", "order 001 data");
    }
}

package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.support.HTTPDataHelper;
import com.technicaltest.icommerceorderservice.support.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceBeanImpl implements OrderServiceBean{

    private OrderRepository orderRepository;

    private KafkaTemplate<String, TOrder> kafkaTemplate;

    @Autowired
    public OrderServiceBeanImpl(OrderRepository repository, KafkaTemplate<String, TOrder> kafkaTemplate) {
        super();
        this.orderRepository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderResponse createOrder(TOrder order) {
        order.setStatus(OrderStatus.INIT.name());
        TOrder initOrder = orderRepository.save(order);
        return HTTPDataHelper.orderResponse(initOrder);
    }

    @Override
    public List<TOrder> findOrderByUuid(UUID uuid) {
        return orderRepository.findByUuid(uuid);
    }
}

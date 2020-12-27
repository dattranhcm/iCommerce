package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.entity.TOrderItems;
import com.technicaltest.icommerceorderservice.repository.OrderItemRepository;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.support.HTTPDataHelper;
import com.technicaltest.icommerceorderservice.support.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceBeanImpl implements OrderServiceBean{
    private final Logger logger = LoggerFactory.getLogger(OrderServiceBeanImpl.class);

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderServiceBeanImpl(OrderRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        super();
        this.orderRepository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderResponse createOrder(TOrder order) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        order.setStatus(OrderStatus.INIT.name());
        order.setOrderCode(UUID.randomUUID().toString().substring(1,6));
        order.setShipAddress("Any where in HCMC.");
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        TOrder initOrder = orderRepository.save(order);
        logger.info("OrderServiceBeanImpl INIT order");

//        BigDecimal totalPrice = new BigDecimal(0);
//        logger.info("SISE of orderItem:" + order.getOrderItems().size());
//        for (TOrderItems orderItem: order.getOrderItems()
//             ) {
//            orderItem.setCreatedAt(new Date());
//            orderItem.setUpdatedAt(new Date());
//            orderItem.setOrder(initOrder);
//            totalPrice.add(orderItem.getItemPrice());
//            logger.info("Info of each orderItem");
//            logger.info(mapper.writeValueAsString(orderItem));
//            orderItemRepository.save(orderItem);
//        }


        logger.info(mapper.writeValueAsString(initOrder));
        fireOrderCreatedEvent(initOrder);
        return HTTPDataHelper.orderResponse(initOrder);
    }

    @Override
    public List<TOrder> findOrderByUuid(UUID uuid) {
        return orderRepository.findByUuid(uuid);
    }

    private void fireOrderCreatedEvent(TOrder initOrder) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        kafkaTemplate.send("order", initOrder.getUuid().toString(), mapper.writeValueAsString(initOrder));
    }
}

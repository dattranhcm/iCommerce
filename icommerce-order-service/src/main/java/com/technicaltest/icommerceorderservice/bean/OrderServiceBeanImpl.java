package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductDto;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.entity.TOrderItems;
import com.technicaltest.icommerceorderservice.repository.OrderItemRepository;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.support.HTTPDataHelper;
import com.technicaltest.icommerceorderservice.support.OrderStatus;
import com.technicaltest.icommerceorderservice.support.SubOrderStatus;
import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
    public OrderResponse createOrder(String userUUID ,List<ProductDto> productOnCart) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TOrder order = new TOrder();
        BigDecimal totalPrice = new BigDecimal(0);
        order.setTotalAmount(totalPrice);
        order.setCustomerId(UUID.fromString(userUUID));
        order.setStatus(OrderStatus.INIT.name());
        order.setOrderCode(UUID.randomUUID().toString().substring(1,6).toUpperCase()); // just generate a string to fill this field.
        order.setShipAddress("Any where in HCMC.");
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        // Create order items

        logger.info("PREPARING TO PERSIS ORDER AND ITEMS: " + mapper.writeValueAsString(order));

    //    logger.info("SUB ITEMS: " + mapper.writeValueAsString(productOnCart));
    //    for (ProductDto p: productOnCart) {

        //    i.setOrder(order); // add thang nay ko add thang duoi >> chi co order table co data

    //    }
    //    order.setOrderItems(Collections.singleton(i)); // add thang nay thi error ko luu dc dua nao het
        TOrder initOrder = orderRepository.saveAndFlush(order);

        TOrderItems i = new TOrderItems();
        i.setItemUuid(productOnCart.get(0).getUuid());
        BigDecimal price = productOnCart.get(0).getPrice().stream().filter(c -> c.isCurrentPrice()).collect(Collectors.toList()).get(0).getPrice();
        i.setItemPrice(price);
        i.setSubOrderAmount(0);
        i.setSubOrderStatus(SubOrderStatus.AVAILABLE.name());
        i.setCreatedAt(new Date());
        i.setUpdatedAt(new Date());
        i.setOrder(initOrder);
        logger.info("SUB ORDER CHUAN BI PERSIS : " + mapper.writeValueAsString(i));
        orderItemRepository.saveAndFlush(i);

    //    fireOrderCreatedEvent(initOrder.getUuid().toString());
        return HTTPDataHelper.orderResponse(initOrder);
    }

    @Override
    public OrderResponse findOrderByUuid(UUID uuid) {
        TOrder order = orderRepository.findByUuid(uuid);
        if(order != null) {
            return new OrderResponse(0, "found", order);
        } else {
            return new OrderResponse(-1, "not found customer", null);
        }
    }

    @Override
    public OrderResponse findOrderByCustomerUuid(UUID customerUuid) {
        List<TOrder> order = orderRepository.findByCustomerId(customerUuid);
        if(order != null) {
            return new OrderResponse(0, "found", order);
        } else {
            return new OrderResponse(-1, "not found customer", null);
        }
    }

    private void fireOrderCreatedEvent(String orderUUID) throws JsonProcessingException {
        kafkaTemplate.send("order", orderUUID);
    }
}

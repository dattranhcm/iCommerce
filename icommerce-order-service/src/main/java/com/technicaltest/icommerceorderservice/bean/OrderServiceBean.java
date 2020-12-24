package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;

import java.util.List;
import java.util.UUID;

public interface OrderServiceBean {
    public OrderResponse createOrder(TOrder order);
    public List<TOrder> findOrderByUuid(UUID uuid);
}

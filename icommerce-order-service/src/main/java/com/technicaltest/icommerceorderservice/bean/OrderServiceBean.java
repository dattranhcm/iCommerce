package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductDto;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.entity.TOrder;

import java.util.List;
import java.util.UUID;

public interface OrderServiceBean {
    public OrderResponse createOrder(String userUUID, List<ProductDto> productOnCart) throws JsonProcessingException;
    public OrderResponse findOrderByUuid(UUID uuid);
    public OrderResponse findOrderByCustomerUuid(UUID customerUuid);
}

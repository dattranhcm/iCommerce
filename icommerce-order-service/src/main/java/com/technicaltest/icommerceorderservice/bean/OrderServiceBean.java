package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TOrder;

import java.util.List;
import java.util.UUID;

public interface OrderServiceBean {
    public List<TOrder> findOrderByUuid(UUID uuid);
}

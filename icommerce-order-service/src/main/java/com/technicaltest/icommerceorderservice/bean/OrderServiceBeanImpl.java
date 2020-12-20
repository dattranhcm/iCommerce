package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceBeanImpl implements OrderServiceBean{

    @Autowired
    private OrderRepository repository;

    @Override
    public List<TOrder> findOrderByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }
}

package com.technicaltest.icommerceorderservice.services;

import com.technicaltest.icommerceorderservice.bean.OrderServiceBean;
import com.technicaltest.icommerceorderservice.repository.OrderRepository;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderServiceBean orderServiceBean;

    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to Order service";
    }

    @GetMapping("/order-detail/{id}")
    public List<TOrder> getOrderDetail(@PathVariable(value = "uuid") UUID orderUuid) {
        return orderServiceBean.findOrderByUuid(orderUuid);
    }
}

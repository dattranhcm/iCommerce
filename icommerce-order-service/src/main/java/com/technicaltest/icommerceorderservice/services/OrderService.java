package com.technicaltest.icommerceorderservice.services;

import com.technicaltest.icommerceorderservice.entity.OrderRepository;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @GetMapping("/welcome")
    public Object getAllUsers() {
        return "Welcome to Order service";
    }

    @GetMapping("/order-detail/{id}")
    public Optional<TOrder> getOrderDetail(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }
}

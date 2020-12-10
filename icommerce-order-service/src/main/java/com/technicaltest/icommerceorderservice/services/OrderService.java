package com.technicaltest.icommerceorderservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderservice")
@RequiredArgsConstructor
public class OrderService {
    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to Order service";
    }
}

package com.technicaltest.icommerceuserservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
public class CustomerService {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Customer service";
    }
}

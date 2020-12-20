package com.technicaltest.icommerceuserservice.services;

import com.technicaltest.icommerceuserservice.bean.CustomerServiceBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerServiceBean customerServiceBean;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Customer service";
    }
}

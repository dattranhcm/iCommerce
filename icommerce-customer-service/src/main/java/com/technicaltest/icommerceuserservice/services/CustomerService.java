package com.technicaltest.icommerceuserservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceuserservice.bean.CustomerServiceBean;
import com.technicaltest.icommerceuserservice.dto.LoginResponse;
import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.dto.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerServiceBean customerServiceBean;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Customer service";
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest requestBody) {
        return customerServiceBean.registration(requestBody);
    }

    @GetMapping("/login")
    public LoginResponse loginByFacebook(@RequestHeader(name = "facebookID") String facebookID, @RequestHeader(name = "facebookToken") String facebookToken) throws IOException {
        logger.info(String.format("facebookID %s, facebookToken %s", facebookID, facebookToken));
        return customerServiceBean.loginByFacebook(facebookID, facebookToken);
    }
}

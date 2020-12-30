package com.technicaltest.icommerceuserservice.services;

import com.technicaltest.icommerceuserservice.bean.CustomerServiceBean;
import com.technicaltest.icommerceuserservice.dto.CustomerResponse;
import com.technicaltest.icommerceuserservice.dto.LoginResponse;
import com.technicaltest.icommerceuserservice.dto.RegistrationRequest;
import com.technicaltest.icommerceuserservice.dto.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 *  Customer service
 *  Provide internal api for Registration, Login, Get Customer information
 * @Author Dat Tran
 */
@RestController
@RequestMapping("/customer-service")
@RequiredArgsConstructor
public class CustomerService {

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerServiceBean customerServiceBean;

    @GetMapping("/health")
    public String welcome() {
        return "Customer service ready now";
    }

    @PostMapping("/registration")
    public RegistrationResponse registration(@RequestBody RegistrationRequest requestBody) {
        return customerServiceBean.registration(requestBody);
    }

    @GetMapping("/login")
    public LoginResponse loginByFacebook(@RequestHeader(name = "facebook-id") String facebookID, @RequestHeader(name = "facebook-token") String facebookToken) throws IOException {
        return customerServiceBean.loginByFacebook(facebookID, facebookToken);
    }

    @GetMapping("/check-customer")
    public CustomerResponse loginByFacebook(@RequestHeader(name = "customer-uuid") UUID customerUUID) throws IOException {
        return customerServiceBean.checkCustomerUUID(customerUUID);
    }
}

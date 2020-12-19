package com.technicaltest.icommerce_gateway.bean;

import com.technicaltest.icommerce_gateway.client.CustomerServiceClient;
import com.technicaltest.icommerce_gateway.client.OrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayBean {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private OrderServiceClient orderServiceClient;

    public Object callCustomerServiceWelcome() {
        return customerServiceClient.customerServiceWelcome();
    }

    public Object callOrderServiceWelcome() {
        return orderServiceClient.orderServiceWelcome();
    }

    public Object orderDetailByID(Long id) {
        return orderServiceClient.orderDetailByID(id);
    }
}

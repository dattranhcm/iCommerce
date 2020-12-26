package com.technicaltest.icommerce_gateway.bean;

import com.technicaltest.icommerce_gateway.client.CustomerServiceClient;
import com.technicaltest.icommerce_gateway.client.OrderClient;
import com.technicaltest.icommerce_gateway.client.OrderServiceClient;
import com.technicaltest.icommerce_gateway.client.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GatewayBean {

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    public Object callCustomerServiceWelcome() {
        return customerServiceClient.customerServiceWelcome();
    }

    public Object callOrderServiceWelcome() {
        return orderServiceClient.orderServiceWelcome();
    }

    public Object orderDetailByID(String uuid) {
        return orderServiceClient.orderDetailByID(uuid);
    }

    public Object productDetailByCode(List<String> codes) {
        return productServiceClient.getProductDetail(codes);
    }
}

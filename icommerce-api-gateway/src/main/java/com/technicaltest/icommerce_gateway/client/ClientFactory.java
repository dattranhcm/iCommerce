package com.technicaltest.icommerce_gateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    private OrderServiceClient orderServiceClient;
    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private ShoppingCartServiceClient shoppingCartServiceClient;

    public CustomerServiceClient getCustomerServiceClient() {
        return customerServiceClient;
    }

    public OrderServiceClient getOrderServiceClient() {
        return orderServiceClient;
    }

    public ProductServiceClient getProductServiceClient() {
        return productServiceClient;
    }

    public ShoppingCartServiceClient getShoppingCartServiceClient() {
        return shoppingCartServiceClient;
    }
}

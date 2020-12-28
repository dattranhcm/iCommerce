package com.technicaltest.icommerce_gateway.bean;

import com.technicaltest.icommerce_gateway.client.CustomerServiceClient;
import com.technicaltest.icommerce_gateway.client.OrderServiceClient;
import com.technicaltest.icommerce_gateway.client.ProductServiceClient;
import com.technicaltest.icommerce_gateway.client.ShoppingCartServiceClient;
import com.technicaltest.icommerce_gateway.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GatewayBean {
    private final Logger logger = LoggerFactory.getLogger(GatewayBean.class);
    @Autowired
    private CustomerServiceClient customerServiceClient;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private ShoppingCartServiceClient shoppingCartServiceClient;

    public RegistrationResponse customerRegistration(RegistrationRequest registrationRequest) {
        logger.info("GO: customerRegistration");
        return customerServiceClient.registration(registrationRequest).block();
    }

    public LoginResponse login(String facebookID, String facebookToken) {
        logger.info("GO: login");
        return customerServiceClient.loginByFacebook(facebookID, facebookToken).block();
    }

    public ProductResponse getProductInfoDetail(List<String> codes) {
        return productServiceClient.getProductDetail(codes).block();
    }

    public ProductResponse getAllProducts() {
        return productServiceClient.getAllProduct().block();
    }

    public OrderResponse orderDetailByID(UUID uuid) {
        return orderServiceClient.orderDetailByID(uuid.toString()).block();
    }

    public OrderResponse orderOfCustomerUUID(UUID customerUUID) {
        return orderServiceClient.orderOfCustomerUUID(customerUUID.toString()).block();
    }

    public ShoppingCart addCartItem(String customerUUID, CartItem cartItem) {
        return shoppingCartServiceClient.addCartItem(customerUUID, cartItem).block();
    }

    public ShoppingCart getCartInfoByCustomerUUID(String customerUUID) {
        return shoppingCartServiceClient.getCartInfoByCustomerUUID(customerUUID).block();
    }

    public OrderResponse createOrderFromShoppingCart(String customerUUID) {
        return shoppingCartServiceClient.createOrderFromShoppingCart(customerUUID).block();
    }
}

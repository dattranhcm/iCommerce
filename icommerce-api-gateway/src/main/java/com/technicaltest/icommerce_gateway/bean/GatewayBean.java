package com.technicaltest.icommerce_gateway.bean;

import com.technicaltest.icommerce_gateway.client.*;
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
    ClientFactory clientFactory;

    public RegistrationResponse customerRegistration(RegistrationRequest registrationRequest) {
        return clientFactory.getCustomerServiceClient().registration(registrationRequest).block();
    }

    public LoginResponse login(String facebookID, String facebookToken) {
        return clientFactory.getCustomerServiceClient().loginByFacebook(facebookID, facebookToken).block();
    }

    public CustomerResponse checkCustomer(String customerUUID) {
        return clientFactory.getCustomerServiceClient().checkCustomer(customerUUID).block();
    }

    public ProductResponse getProductInfoDetail(List<String> codes) {
        return clientFactory.getProductServiceClient().getProductDetail(codes).block();
    }

    public ProductResponse getAllProducts() {
        return clientFactory.getProductServiceClient().getAllProduct().block();
    }

    public OrderResponse orderDetailByID(UUID uuid) {
        return clientFactory.getOrderServiceClient().orderDetailByID(uuid.toString()).block();
    }

    public OrderResponse orderOfCustomerUUID(UUID customerUUID) {
        return clientFactory.getOrderServiceClient().orderOfCustomerUUID(customerUUID.toString()).block();
    }

    public ShoppingCart addCartItem(String customerUUID, CartItem cartItem) {
        return clientFactory.getShoppingCartServiceClient().addCartItem(customerUUID, cartItem).block();
    }

    public ShoppingCart getCartInfoByCustomerUUID(String customerUUID) {
        return clientFactory.getShoppingCartServiceClient().getCartInfoByCustomerUUID(customerUUID).block();
    }

    public OrderResponse createOrderFromShoppingCart(String customerUUID) {
        return clientFactory.getShoppingCartServiceClient().createOrderFromShoppingCart(customerUUID).block();
    }

    public Object getCustomerInfoByUUID(String customerUUID) {
        return clientFactory.getCustomerServiceClient().checkCustomer(customerUUID);
    }
}

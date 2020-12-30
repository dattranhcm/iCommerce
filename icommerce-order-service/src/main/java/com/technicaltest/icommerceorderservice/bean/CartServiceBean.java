package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;

import java.util.List;

public interface CartServiceBean {
    public ShoppingCart addItemToCart(String userUUID, CartItem product);
    public ShoppingCart getCart(String userUUID);
    public OrderResponse createOrderFromCart(String userUUID) throws JsonProcessingException;
    public ProductResult fetchProductDetailByProductCode(List<String> productCodes) throws JsonProcessingException;
}

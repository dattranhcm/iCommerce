package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceorderservice.client.ProductServiceClient;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartRedisRepository;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;
import com.technicaltest.icommerceorderservice.support.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceBeanImpl implements CartServiceBean {
    private final Logger logger = LoggerFactory.getLogger(CartServiceBeanImpl.class);
    @Autowired
    private CartRedisRepository cartRedisRepository;

    @Autowired
    private OrderServiceBean orderServiceBean;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ShoppingCart addItemToCart(String userUUID, CartItem product) {

        ShoppingCart shoppingCart = getCart(userUUID);
        if (shoppingCart == null){
            List<CartItem> items = new ArrayList<>();
            items.add(product);
            shoppingCart = new ShoppingCart(userUUID, items);
        } else {
            shoppingCart.getProductsInCart().add(product);
        }
        cartRedisRepository.add(shoppingCart);
        return cartRedisRepository.findCart(userUUID);
    }

    @Override
    public ShoppingCart getCart(String userUuId) {
        return cartRedisRepository.findCart(userUuId);
    }

    @Override
    public OrderResponse createOrderFromCart(String userUUID) throws JsonProcessingException {
        ShoppingCart cart = getCart(userUUID);
        if (cart == null) {
            return new OrderResponse(-2, Constant.ITEM_NOT_FOUND, null);
        }
        List<String> productOnCart = cart.getProductsInCart().stream().map(CartItem::getProductCode).collect(Collectors.toList());
        ProductResult productResult = fetchProductDetailByProductCode(productOnCart);
        OrderResponse createdOrder = orderServiceBean.createOrder(userUUID ,productResult.getData());
        if (createdOrder != null) {
            cartRedisRepository.delete(userUUID);
        }
        return createdOrder;
    }

    @Override
    public ProductResult fetchProductDetailByProductCode(List<String> productCodes) throws JsonProcessingException {
        return productServiceClient.getProductDetail(productCodes).block();
    }
}

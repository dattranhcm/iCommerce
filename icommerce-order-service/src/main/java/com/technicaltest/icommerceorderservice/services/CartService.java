package com.technicaltest.icommerceorderservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.technicaltest.icommerceorderservice.bean.CartServiceBean;
import com.technicaltest.icommerceorderservice.bean.OrderServiceBean;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.events.OrderKafkaListener;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;
import com.technicaltest.icommerceorderservice.support.HeaderGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cart-service")
@RequiredArgsConstructor
public class CartService {
    private final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    CartServiceBean cartServiceBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Cart service";
    }

    @PostMapping(value = "/add-cart")
    public ShoppingCart addCart(@RequestHeader(value = "customer-uuid") String customerUUID,
                                @RequestBody CartItem item){
        return cartServiceBean.addItemToCart(customerUUID, item);
    }

    @GetMapping(value = "/cart")
    public ShoppingCart getCart(@RequestHeader(value = "customer-uuid") String customerUUID) {
        return cartServiceBean.getCart(customerUUID);
    }

    @PostMapping(value = "/create-order")
    public OrderResponse createOrder(@RequestHeader(value = "customer-uuid") String userUUID) throws JsonProcessingException {
        return cartServiceBean.createOrderFromCart(userUUID);
    }
}

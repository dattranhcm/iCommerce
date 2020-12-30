package com.technicaltest.icommerceorderservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicaltest.icommerceorderservice.bean.CartServiceBean;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 *  Allow api to add shopping cart, view shopping cart, create order from shopping cart
 * @Author Dat Tran
 */
@RestController
@RequestMapping("/cart-service")
public class CartService {
    private final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    CartServiceBean cartServiceBean;

    @GetMapping("/health")
    public String welcome() {
        return "Cart service is ready now";
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

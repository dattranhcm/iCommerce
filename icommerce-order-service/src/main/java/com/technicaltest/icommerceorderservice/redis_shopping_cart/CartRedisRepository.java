package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import java.util.Map;

public interface CartRedisRepository {
    /**
     * Return all shopping cart
     */
    Map<Object, Object> findAllCarts();

    /**
     * Add key-value pair to Redis.
     */
    void add(ShoppingCart shoppingCart);

    /**
     * Delete a key-value pair in Redis.
     */
    void delete(String id);

    /**
     * Find a shopping cart in Redis.
     */
    ShoppingCart findCart(String id);
}

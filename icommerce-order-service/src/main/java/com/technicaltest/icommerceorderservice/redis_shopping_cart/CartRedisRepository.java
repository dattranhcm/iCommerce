package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import java.util.Map;

public interface CartRedisRepository {
    /**
     * Return all shopping cart
     */
    Map<Object, Object> findAllCarts();

    /**
     * Add shopping cart item to Redis.
     */
    void add(ShoppingCart shoppingCart);

    /**
     * Delete a shopping cart item in Redis.
     */
    void delete(String id);

    /**
     * Find a shopping cart in Redis.
     */
    ShoppingCart findCart(String id);
}

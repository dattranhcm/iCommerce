package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import java.util.Collection;
import java.util.Map;

public interface CartRedisRepository {
    /**
     * Return all movies
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
     * find a movie
     */
    ShoppingCart findCart(String id);
}

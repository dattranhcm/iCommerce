package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class CartRedisRepositoryImpl implements CartRedisRepository {
    private static final String KEY = "ShoppingCart";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public CartRedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final ShoppingCart cart) {
        hashOperations.put(KEY, cart.getUserUuid(), cart);
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    public ShoppingCart findCart(final String id) {
        return (ShoppingCart) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllCarts() {
        return hashOperations.entries(KEY);
    }
}

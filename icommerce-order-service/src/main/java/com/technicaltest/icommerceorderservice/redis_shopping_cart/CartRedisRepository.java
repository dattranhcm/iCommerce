package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import java.util.Collection;

public interface CartRedisRepository {
    public void addItemToCart(String key, Object item);
    public Collection<Object> getCart(String key, Class type);
    public void deleteItemFromCart(String key, Object item);
    public void deleteCart(String key);
}

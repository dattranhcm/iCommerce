package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TProduct;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceBeanImpl implements CartServiceBean {

    @Autowired
    private CartRedisRepository cartRedisRepository;

    @Override
    public void addItemToCart(String cartId, TProduct product) {
        cartRedisRepository.addItemToCart(cartId, product);
    }

    @Override
    public List<Object> getCart(String cartId) {
        return (List<Object>)cartRedisRepository.getCart(cartId, TProduct.class);
    }

    @Override
    public void deleteItemFromCart(String cartId, UUID productUuid) {
        List<TProduct> cart = (List) cartRedisRepository.getCart(cartId, TProduct.class);
        for(TProduct item : cart){
            if((item.getUuid()).equals(productUuid)){
                cartRedisRepository.deleteItemFromCart(cartId, item);
            }
        }
    }

    @Override
    public boolean checkIfItemIsExist(String cartId, UUID productUuid) {
        List<TProduct> cart = (List) cartRedisRepository.getCart(cartId, TProduct.class);
        for(TProduct item : cart){
            if((item.getUuid()).equals(productUuid)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TProduct> getAllItemsFromCart(String cartId) {
        List<TProduct> items = (List)cartRedisRepository.getCart(cartId, TProduct.class);
        return items;
    }

    @Override
    public void deleteCart(String cartId) {
        cartRedisRepository.deleteCart(cartId);
    }
}

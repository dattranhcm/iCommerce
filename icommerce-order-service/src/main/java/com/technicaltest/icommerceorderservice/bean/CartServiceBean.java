package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TProduct;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;

import java.util.List;
import java.util.UUID;

public interface CartServiceBean {
    public ShoppingCart addItemToCart(String userUUID, CartItem product);
    public ShoppingCart getCart(String userUUID);
//    public void deleteItemFromCart(String cartId, UUID productUuid);
//    public boolean checkIfItemIsExist(String cartId, UUID productUuid);
//    public List<TProduct> getAllItemsFromCart(String cartId);
//    public void deleteCart(String cartId);
}

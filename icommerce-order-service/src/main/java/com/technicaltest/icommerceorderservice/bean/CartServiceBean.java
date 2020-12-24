package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;

public interface CartServiceBean {
    public ShoppingCart addItemToCart(String userUUID, CartItem product);
    public ShoppingCart getCart(String userUUID);
    public TOrder createOrderFromCart(String userUUID);
//    public void deleteItemFromCart(String cartId, UUID productUuid);
//    public boolean checkIfItemIsExist(String cartId, UUID productUuid);
//    public List<TProduct> getAllItemsFromCart(String cartId);
//    public void deleteCart(String cartId);
}

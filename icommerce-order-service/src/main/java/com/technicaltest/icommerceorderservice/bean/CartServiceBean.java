package com.technicaltest.icommerceorderservice.bean;

import com.technicaltest.icommerceorderservice.entity.TProduct;

import java.util.List;
import java.util.UUID;

public interface CartServiceBean {
    public void addItemToCart(String cartId, TProduct product);
    public List<Object> getCart(String cartId);
    public void deleteItemFromCart(String cartId, UUID productUuid);
    public boolean checkIfItemIsExist(String cartId, UUID productUuid);
    public List<TProduct> getAllItemsFromCart(String cartId);
    public void deleteCart(String cartId);
}

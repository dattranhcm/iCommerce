package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import com.technicaltest.icommerceorderservice.entity.TProduct;

import java.util.List;

public class ShoppingCart {

    private String userUuid;
    private List<TProduct> productInCart;

    public ShoppingCart(String userUuid, List<TProduct> productInCart) {
        this.userUuid = userUuid;
        this.productInCart = productInCart;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public List<TProduct> getProductInCart() {
        return productInCart;
    }

    public void setProductInCart(List<TProduct> productInCart) {
        this.productInCart = productInCart;
    }
}

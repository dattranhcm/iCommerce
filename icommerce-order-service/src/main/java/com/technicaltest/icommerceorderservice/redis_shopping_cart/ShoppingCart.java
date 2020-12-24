package com.technicaltest.icommerceorderservice.redis_shopping_cart;


import java.io.Serializable;
import java.util.List;

public class ShoppingCart implements Serializable {

    private String userUuid;
    private List<CartItem> productsInCart;

    public ShoppingCart(){};

    public ShoppingCart(String userUuid, List<CartItem> productsInCart) {
        this.userUuid = userUuid;
        this.productsInCart = productsInCart;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public List<CartItem> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<CartItem> productsInCart) {
        this.productsInCart = productsInCart;
    }
}

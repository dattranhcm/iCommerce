package com.technicaltest.icommerceorderservice.redis_shopping_cart;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private String productUUID;
    private String productName;
    private BigDecimal price;

    public CartItem(String productUUID, String productName, BigDecimal price) {
        this.productUUID = productUUID;
        this.productName = productName;
        this.price = price;
    }

    public String getProductUUID() {
        return productUUID;
    }

    public void setProductUUID(String productUUID) {
        this.productUUID = productUUID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

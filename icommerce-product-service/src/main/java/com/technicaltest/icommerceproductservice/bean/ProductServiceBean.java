package com.technicaltest.icommerceproductservice.bean;

import com.technicaltest.icommerceproductservice.entity.TProduct;

import java.util.List;

public interface ProductServiceBean {
    public TProduct getProductInfoByID(String productUuid);
    public List<TProduct> getProductInfoByCode(List<String> productCode);
    public List<TProduct> findAll();
}

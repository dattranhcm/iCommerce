package com.technicaltest.icommerceproductservice.bean;

import com.technicaltest.icommerceproductservice.entity.TProduct;
import com.technicaltest.icommerceproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceBeamImpl implements ProductServiceBean {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public TProduct getProductInfoByID(String productUuid) {
        return productRepository.findByUuid(productUuid);
    }

    @Override
    public TProduct getProductInfoByCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    @Override
    public List<TProduct> findAll() {
        return productRepository.findAll();
    }
}

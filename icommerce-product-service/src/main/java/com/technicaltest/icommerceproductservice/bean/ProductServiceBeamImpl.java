package com.technicaltest.icommerceproductservice.bean;

import com.technicaltest.icommerceproductservice.entity.TProduct;
import com.technicaltest.icommerceproductservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceBeamImpl implements ProductServiceBean {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceBeamImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public TProduct getProductInfoByID(String productUuid) {
        return productRepository.findByUuid(productUuid);
    }

    @Override
    public List<TProduct> getProductInfoByCode(List<String> productCode) {
        List<TProduct> pr = productRepository.findByProductCodeIn(productCode);
        return pr;
    }

    @Override
    public List<TProduct> findAll() {
        List<TProduct> ls = productRepository.findAll();
        return ls;
    }
}

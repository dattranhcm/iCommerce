package com.technicaltest.icommerceproductservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceproductservice.bean.ProductServiceBean;
import com.technicaltest.icommerceproductservice.dto.ProductResponse;
import com.technicaltest.icommerceproductservice.entity.TProduct;
import com.technicaltest.icommerceproductservice.support.HeaderGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductServiceBean productServiceBean;

    @GetMapping("/health")
    public String getAllUsers() {
        return "Product service ready now";
    }

    @GetMapping("/product")
    public ProductResponse findProductByCode(@RequestParam(name = "codes") List<String> productCodes) throws JsonProcessingException {
        List<TProduct> result = productServiceBean.getProductInfoByCode(productCodes);
        if (result != null) {
            return new ProductResponse(0, "", result);
        }
        return new ProductResponse(-1, "Not found product has code: " + productCodes, null);
    }

    @GetMapping("/product-all")
    public ProductResponse fetchAllProduct() {
        List<TProduct> result = productServiceBean.findAll();
        if (result != null && result.size() > 0) {
            return new ProductResponse(0, "", result);
        }
        return new ProductResponse(-1, "Not found any product", null);
    }

}

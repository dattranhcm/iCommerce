package com.technicaltest.icommerceproductservice.service;

import com.technicaltest.icommerceproductservice.bean.ProductServiceBean;
import com.technicaltest.icommerceproductservice.dto.ProductResponse;
import com.technicaltest.icommerceproductservice.entity.TProduct;
import com.technicaltest.icommerceproductservice.support.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductService {

    @Autowired
    private ProductServiceBean productServiceBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to Product service";
    }

    @GetMapping("/product2/{code}")
    public Object findProductByCode2(@PathVariable(name = "code") String productCode) {
        TProduct result = productServiceBean.getProductInfoByCode(productCode);
        return result;
    }

    @GetMapping("/product-all2")
    public Object fetchAllProduct2() {
        List<TProduct> result = productServiceBean.findAll();
        return result;
    }

    @GetMapping("/product/{code}")
    public ResponseEntity<ProductResponse> findProductByCode(@PathVariable(name = "code") String productCode) {
        TProduct result = productServiceBean.getProductInfoByCode(productCode);
        if (result != null) {
            return new ResponseEntity<ProductResponse>(
                    new ProductResponse(0, "", result),
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ProductResponse>(
                new ProductResponse(-1, "Not found product has code: " + productCode, null),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @GetMapping("/product-all")
    public ResponseEntity<ProductResponse> fetchAllProduct() {
        List<TProduct> result = productServiceBean.findAll();
        if (result != null && result.size() > 0) {
            return new ResponseEntity<ProductResponse>(
                    new ProductResponse(0, "", result),
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ProductResponse>(
                new ProductResponse(-1, "Not found any product", null),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

}

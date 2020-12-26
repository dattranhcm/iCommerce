package com.technicaltest.icommerceproductservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceproductservice.bean.ProductServiceBean;
import com.technicaltest.icommerceproductservice.dto.ProductResponse;
import com.technicaltest.icommerceproductservice.entity.TProduct;
import com.technicaltest.icommerceproductservice.support.HTTPDataHelper;
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

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to Product service";
    }

    @GetMapping("/product")
    public ResponseEntity<ProductResponse> findProductByCode(@RequestParam(name = "codes") List<String> productCodes) throws JsonProcessingException {
        logger.info("Product service -> findProductByCode: OK");
        ObjectMapper mapper = new ObjectMapper();
        logger.info(mapper.writeValueAsString(productCodes));
        List<TProduct> result = productServiceBean.getProductInfoByCode(productCodes);
        logger.info(mapper.writeValueAsString(result));
        if (result != null) {
            return new ResponseEntity<ProductResponse>(
                    new ProductResponse(0, "", result),
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ProductResponse>(
                new ProductResponse(-1, "Not found product has code: " + productCodes, null),
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

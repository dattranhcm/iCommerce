package com.technicaltest.icommerceorderservice.client;

import com.technicaltest.icommerceorderservice.services.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceClient.class);
    private String hostname = "http://product-service:8080/product-service/";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Object> fetchProductByCodes(List<String> productCodes) {
        logger.info(hostname + "product?codes=" + productCodes);
        return webClientBuilder.build()
                .get()
                .uri(hostname + "product?codes=" + productCodes)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Object.class);
    }

    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

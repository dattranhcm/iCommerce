package com.technicaltest.icommerce_gateway.client;

import com.technicaltest.icommerce_gateway.dto.OrderResponse;
import com.technicaltest.icommerce_gateway.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {
    private String hostname = "http://product-service:8080/product-service/";

    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<ProductResponse> getProductDetail(List<String> code) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "/product?codes=" + String.join(",", code))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ProductResponse.class);
    }

    public Mono<ProductResponse> getAllProduct() {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "/product-all")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ProductResponse.class);
    }
    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

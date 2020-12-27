package com.technicaltest.icommerceorderservice.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceorderservice.bean.CartServiceBeanImpl;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
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

    public Mono<Object> customerServiceWelcome() {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "welcome")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<ProductResult> getProductDetail(List<String> code) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        logger.info(mapper.writeValueAsString(hostname + "product?codes=" + String.join(",", code)));
        Mono<ProductResult> rs = webClientBuilder.build()
                .get()
                .uri(hostname + "product?codes=" + String.join(",", code))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(ProductResult.class);
        logger.info(mapper.writeValueAsString(rs));
        return rs;
    }
    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

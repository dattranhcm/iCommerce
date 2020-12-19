package com.technicaltest.icommerce_gateway.client;

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
public class OrderServiceClient {
    // Could be changed for testing purpose
    private String hostname = "http://order-service:8080/order-service/";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<String> orderServiceWelcome() {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "welcome")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<Object> orderDetailByID(Long id) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "order-detail/" + id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Object.class);
    }
    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

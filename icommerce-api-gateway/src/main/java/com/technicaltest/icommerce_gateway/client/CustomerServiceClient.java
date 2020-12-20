package com.technicaltest.icommerce_gateway.client;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerServiceClient {

    private String hostname = "http://customer-service:8080/customer-service/";

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

    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

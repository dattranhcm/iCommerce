package com.technicaltest.icommerce_gateway.client;

import com.technicaltest.icommerce_gateway.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {
    private String hostname = "http://order-service:8080/order-service/";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<OrderResponse> orderDetailByID(String uuid) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "order-detail/" + uuid)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(OrderResponse.class);
    }

    public Mono<OrderResponse> orderOfCustomerUUID(String customerUUID) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "order-detail-by-customer/" + customerUUID)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(OrderResponse.class);
    }

    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

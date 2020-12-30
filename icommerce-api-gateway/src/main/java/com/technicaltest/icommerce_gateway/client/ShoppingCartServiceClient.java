package com.technicaltest.icommerce_gateway.client;

import com.technicaltest.icommerce_gateway.dto.CartItem;
import com.technicaltest.icommerce_gateway.dto.OrderResponse;
import com.technicaltest.icommerce_gateway.dto.ShoppingCart;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ShoppingCartServiceClient {

    @Value("${icommerce.url.cart-service}")
    private String hostname;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<ShoppingCart> addCartItem(String customerUUID, CartItem cartItem) {
        return webClientBuilder.build()
                .post()
                .uri(hostname + "add-cart")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("customer-uuid", customerUUID)
                .body(Mono.just(cartItem), CartItem.class)
                .retrieve()
                .bodyToMono(ShoppingCart.class);
    }

    public Mono<ShoppingCart> getCartInfoByCustomerUUID(String customerUUID) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "cart")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("customer-uuid", customerUUID)
                .retrieve()
                .bodyToMono(ShoppingCart.class);
    }

    public Mono<OrderResponse> createOrderFromShoppingCart(String customerUUID) {
        return webClientBuilder.build()
                .post()
                .uri(hostname + "create-order")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("customer-uuid", customerUUID)
                .retrieve()
                .bodyToMono(OrderResponse.class);
    }

}

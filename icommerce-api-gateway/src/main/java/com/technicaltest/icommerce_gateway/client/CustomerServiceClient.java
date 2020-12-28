package com.technicaltest.icommerce_gateway.client;

import com.technicaltest.icommerce_gateway.bean.GatewayBean;
import com.technicaltest.icommerce_gateway.dto.LoginResponse;
import com.technicaltest.icommerce_gateway.dto.RegistrationRequest;
import com.technicaltest.icommerce_gateway.dto.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerServiceClient {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceClient.class);
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

    public Mono<LoginResponse> loginByFacebook(String facebookID, String facebookToken) {
        logger.info("GO: loginByFacebook " + facebookID + " " + facebookToken);
        return webClientBuilder.build()
                .get()
                .uri(hostname + "login")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("facebookID", facebookID)
                .header("facebookToken", facebookToken)
                .retrieve()
                .bodyToMono(LoginResponse.class);
    }

    public Mono<RegistrationResponse> registration(RegistrationRequest registrationRequest) {
        logger.info("GO: registration ");
        return webClientBuilder.build()
                .post()
                .uri(hostname + "registration")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(registrationRequest), RegistrationRequest.class)
                .retrieve()
                .bodyToMono(RegistrationResponse.class);
    }

    void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

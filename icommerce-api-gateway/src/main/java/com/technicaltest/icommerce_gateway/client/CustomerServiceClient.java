package com.technicaltest.icommerce_gateway.client;

import com.technicaltest.icommerce_gateway.dto.CustomerResponse;
import com.technicaltest.icommerce_gateway.dto.LoginResponse;
import com.technicaltest.icommerce_gateway.dto.RegistrationRequest;
import com.technicaltest.icommerce_gateway.dto.RegistrationResponse;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CustomerServiceClient {
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceClient.class);
    @Value("${icommerce.url.customer-service}")
    private String hostname;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<LoginResponse> loginByFacebook(String facebookID, String facebookToken) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "login")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("facebook-id", facebookID)
                .header("facebook-token", facebookToken)
                .retrieve()
                .bodyToMono(LoginResponse.class);
    }

    public Mono<CustomerResponse> checkCustomer(String customerUUID) {
        return webClientBuilder.build()
                .get()
                .uri(hostname + "check-customer")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("customer-uuid", customerUUID)
                .retrieve()
                .bodyToMono(CustomerResponse.class);
    }

    public Mono<RegistrationResponse> registration(RegistrationRequest registrationRequest) {
        logger.info("hostname is " + hostname);
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

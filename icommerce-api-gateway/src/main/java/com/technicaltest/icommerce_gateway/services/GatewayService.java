package com.technicaltest.icommerce_gateway.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerce_gateway.bean.GatewayBean;
import com.technicaltest.icommerce_gateway.common.CommonActivities;
import com.technicaltest.icommerce_gateway.dto.*;
import com.technicaltest.icommerce_gateway.helper.HeaderGenerator;
import com.technicaltest.icommerce_gateway.helper.JWTHelper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/gateway-service")
@RequiredArgsConstructor
public class GatewayService {
    private final Logger logger = LoggerFactory.getLogger(GatewayService.class);

    @Autowired
    private GatewayBean gatewayBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/customer-registration")
    public RegistrationResponse customerRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return gatewayBean.customerRegistration(registrationRequest);
    }

    @GetMapping(value = "/login")
    public LoginResponse login(@RequestHeader(name = "facebook-id") String facebookID, @RequestHeader(name = "facebook-token") String facebookToken) {
        return gatewayBean.login(facebookID, facebookToken);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<ProductResponse> getProductByCodes(@RequestHeader(name = "access-token") String jwtAccessToken,
                                             @RequestParam(value = "codes") List<String> codes) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<ProductResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }

        UserActivity userActivity = new UserActivity(UUID.randomUUID().toString(), CommonActivities.SEARCH.name(), String.join(",", codes));
        customerActivityEvent(objectMapper.writeValueAsString(userActivity));
        return new ResponseEntity<ProductResponse>(
                gatewayBean.getProductInfoDetail(codes),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/all-products")
    public ResponseEntity<ProductResponse> getAllProducts(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<ProductResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ProductResponse>(
                gatewayBean.getAllProducts(),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/order-detail")
    public ResponseEntity<OrderResponse> orderDetailByID(@RequestHeader(name = "access-token") String jwtAccessToken,
                                         @RequestParam(name = "order-uuid") UUID uuid) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<OrderResponse>(
                gatewayBean.orderDetailByID(uuid),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/order-detail-of-customer")
    public ResponseEntity<OrderResponse> orderDetailByCustomerUUID(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<OrderResponse>(
                gatewayBean.orderOfCustomerUUID(UUID.fromString(customerUUID)),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @PostMapping(value = "add-cart-item")
    public ResponseEntity<ShoppingCart> addCartItem(@RequestHeader(name = "access-token") String jwtAccessToken,
                                    @RequestBody CartItem cartItem) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<ShoppingCart>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        UserActivity userActivity = new UserActivity(UUID.randomUUID().toString(), CommonActivities.ADD_CART.name(), objectMapper.writeValueAsString(cartItem));
        customerActivityEvent(objectMapper.writeValueAsString(userActivity));
        return new ResponseEntity<ShoppingCart>(
                gatewayBean.addCartItem(customerUUID.toString(), cartItem),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @GetMapping("shopping-cart-of-customer")
    public ResponseEntity<ShoppingCart> getCartInfoByCustomerUUID(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<ShoppingCart>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ShoppingCart>(
                gatewayBean.getCartInfoByCustomerUUID(customerUUID.toString()),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderResponse> createOrderFromShoppingCart(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        if (gatewayBean.checkCustomer(customerUUID).getCode() != 0) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<OrderResponse>(
                gatewayBean.createOrderFromShoppingCart(customerUUID.toString()),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    private void customerActivityEvent(String userActivity) {
        kafkaTemplate.send("tracking-customer-activity", userActivity);
    }
}

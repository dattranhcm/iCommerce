package com.technicaltest.icommerce_gateway.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerce_gateway.bean.GatewayBean;
import com.technicaltest.icommerce_gateway.common.CommonActivities;
import com.technicaltest.icommerce_gateway.dto.*;
import com.technicaltest.icommerce_gateway.helper.HeaderGenerator;
import com.technicaltest.icommerce_gateway.helper.JWTHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * This is API-GATEWAY layer of ICommerce Backend
 * with simple authentication with JWT after login success
 * The Gateway service provide api to serve very basic of Icommerce Backend including the steps below:
 *  1 Customer registration with 'customer-registration' api
 *  2 Customer login with facebookId and facebookToken with 'login' api
 *  3.1 View Products by product codes (after customer login success and has jwt token) with 'products' api
 *  3.2 View All Products by product codes (after customer login success and has jwt token) with 'all-products' api
 *  4 Allow customer add an item(product) to their shopping cart with 'add-cart-item' api
 *  5 Allow customer view their shopping cart with 'shopping-cart-of-customer' api
 *  6 Allow customer create order with existed item in their shopping cart  with 'create-order' api
 *  7 Allow customer view/follow their order after created success with 'order-detail-of-customer' api
 *
 * Author by: Dat Tran
 */

@RestController
@RequestMapping("/gateway-service")
public class GatewayService {

    @Autowired
    private GatewayBean gatewayBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Allow customer register their account
     * @param registrationRequest
     * @Param-Type RegistrationRequest
     * @return RegistrationResponse
     */
    @PostMapping(value = "/customer-registration")
    public RegistrationResponse customerRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return gatewayBean.customerRegistration(registrationRequest);
    }

    /**
     * Customer login with facebookId and facebookToken
     * @header-param facebook-id
     * @header-param facebook-token
     * @return LoginResponse
     */
    @GetMapping(value = "/login")
    public LoginResponse login(@RequestHeader(name = "facebook-id") String facebookID, @RequestHeader(name = "facebook-token") String facebookToken) {
        return gatewayBean.login(facebookID, facebookToken);
    }

    /**
     * View Products by product codes
     * @header-param access-token: the jwt we got after login success
     * @request-param codes
     * @return ProductResponse
     * @throws IOException
     */
    @GetMapping(value = "/products")
    public ResponseEntity<ProductResponse> getProductByCodes(@RequestHeader(name = "access-token") String jwtAccessToken,
                                             @RequestParam(value = "codes") List<String> codes) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
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

    /**
     * View All Products by product codes
     * @header-param access-token: the jwt we got after login success
     * @return ProductResponse
     * @throws IOException
     */
    @GetMapping(value = "/all-products")
    public ResponseEntity<ProductResponse> getAllProducts(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<ProductResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<ProductResponse>(
                gatewayBean.getAllProducts(),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    /**
     * Allow customer add their item/product into shopping cart
     * @header-param access-token: the jwt we got after login success
     * @request-body CartItem
     * @return ShoppingCart
     * @throws IOException
     */
    @PostMapping(value = "add-cart-item")
    public ResponseEntity<ShoppingCart> addCartItem(@RequestHeader(name = "access-token") String jwtAccessToken,
                                    @RequestBody CartItem cartItem) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<ShoppingCart>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        UserActivity userActivity = new UserActivity(UUID.randomUUID().toString(), CommonActivities.ADD_CART.name(), objectMapper.writeValueAsString(cartItem));
        customerActivityEvent(objectMapper.writeValueAsString(userActivity));
        return new ResponseEntity<ShoppingCart>(
                gatewayBean.addCartItem(customerUUID.toString(), cartItem),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    /**
     * Allow customer view their shopping cart
     * @header-param access-token: the jwt we got after login success
     * @return ShoppingCart
     * @throws IOException
     */
    @GetMapping("shopping-cart-of-customer")
    public ResponseEntity<ShoppingCart> getCartInfoByCustomerUUID(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<ShoppingCart>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        return new ResponseEntity<ShoppingCart>(
                gatewayBean.getCartInfoByCustomerUUID(customerUUID.toString()),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    /**
     * Allow customer create an order after from their shopping cart
     * @header-param access-token: the jwt we got after login success
     * @return OrderResponse
     * @throws IOException
     */
    @PostMapping("create-order")
    public ResponseEntity<OrderResponse> createOrderFromShoppingCart(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        return new ResponseEntity<OrderResponse>(
                gatewayBean.createOrderFromShoppingCart(customerUUID.toString()),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    /**
     * Allow customer view a their order info by order uuid
     * @header-param access-token: the jwt we got after login success
     * @request-param order-uuid
     * @return OrderResponse
     * @throws IOException
     */
    @GetMapping(value = "/order-detail")
    public ResponseEntity<OrderResponse> orderDetailByID(@RequestHeader(name = "access-token") String jwtAccessToken,
                                                         @RequestParam(name = "order-uuid") UUID uuid) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<OrderResponse>(
                gatewayBean.orderDetailByID(uuid),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    /**
     * Allow customer view a their order
     * @header-param access-token: the jwt we got after login success
     * @return OrderResponse
     * @throws IOException
     */
    @GetMapping(value = "/order-detail-of-customer")
    public ResponseEntity<OrderResponse> orderDetailByCustomerUUID(@RequestHeader(name = "access-token") String jwtAccessToken) throws IOException {
        if (!JWTHelper.isValidToken(jwtAccessToken)) {
            return new ResponseEntity<OrderResponse>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.UNAUTHORIZED);
        }
        Claims claims = JWTHelper.decodeJWT(jwtAccessToken);
        String customerUUID = claims.getSubject();
        return new ResponseEntity<OrderResponse>(
                gatewayBean.orderOfCustomerUUID(UUID.fromString(customerUUID)),
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }

    private void customerActivityEvent(String userActivity) {
        kafkaTemplate.send("tracking-customer-activity", userActivity);
    }
}

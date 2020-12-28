package com.technicaltest.icommerce_gateway.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerce_gateway.bean.GatewayBean;
import com.technicaltest.icommerce_gateway.common.CommonActivities;
import com.technicaltest.icommerce_gateway.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/gateway-service")
@RequiredArgsConstructor
//@Tag(name = "user")
public class GatewayService {
    private final Logger logger = LoggerFactory.getLogger(GatewayService.class);
    @Autowired
    private GatewayBean gatewayBean;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;
//
//    @Autowired
//    private ProductClient productClient;

//    @Autowired
//    private OrderClient orderClient;

//    @Operation(description = "Xem danh sách User", responses = {
//            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = String.class))), responseCode = "200") })
//    @ApiResponses(value = {
//            @ApiResponse(responseCode  = "200", description = "Thành công"),
//            @ApiResponse(responseCode  = "401", description = "Chưa xác thực"),
//            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm"),
//            @ApiResponse(responseCode  = "404", description = "Không tìm thấy")
//    })
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to gateway service";
    }

    @PostMapping(value = "/customer-registration")
    public RegistrationResponse customerRegistration(@RequestBody RegistrationRequest registrationRequest) {
        logger.info("GO: customerRegistration");
        return gatewayBean.customerRegistration(registrationRequest);
    }

    @GetMapping(value = "/login")
    public LoginResponse login(@RequestHeader(name = "facebook-id") String facebookID, @RequestHeader(name = "facebook-token") String facebookToken) {
        logger.info("GO: login");
        return gatewayBean.login(facebookID, facebookToken);
    }

    @GetMapping(value = "/products")
    public ProductResponse getProductByCodes(@RequestParam(value = "codes") List<String> codes) throws JsonProcessingException {
        logger.info("GO: getProductByCodes");
        UserActivity userActivity = new UserActivity(UUID.randomUUID().toString(), CommonActivities.SEARCH.name(), String.join(",", codes));
        String xx = objectMapper.writeValueAsString(userActivity);
        logger.info("PREPARING SAVE " + xx);
        customerActivityEvent(xx);
        return gatewayBean.getProductInfoDetail(codes);
    }

    @GetMapping(value = "/all-products")
    public ProductResponse getAllProducts() {
        logger.info("GO: getAllProducts");
        return gatewayBean.getAllProducts();
    }

    @GetMapping(value = "/order-detail")
    public OrderResponse orderDetailByID(@RequestParam(name = "order-uuid") UUID uuid) {
        return gatewayBean.orderDetailByID(uuid);
    }

    @GetMapping(value = "/order-detail-of-customer")
    public OrderResponse orderDetailByCustomerUUID(@RequestHeader(name = "customer-uuid") UUID customerUUID) {
        return gatewayBean.orderOfCustomerUUID(customerUUID);
    }

    @PostMapping(value = "add-cart-item")
    public ShoppingCart addCartItem(@RequestHeader(name = "customer-uuid") UUID customerUUID, @RequestBody CartItem cartItem) {
//        try {
//            customerActivityEvent(new UserActivity(customerUUID.toString(), CommonActivities.ADD_CART.name(), objectMapper.writeValueAsString(cartItem)).toString());
//        } catch (JsonProcessingException ex) {
//            logger.error(ex.getMessage());
//        }
        return gatewayBean.addCartItem(customerUUID.toString(), cartItem);
    }

    @GetMapping("shopping-cart-of-customer")
    public ShoppingCart getCartInfoByCustomerUUID(@RequestHeader(name = "customer-uuid") UUID customerUUID) {
        return gatewayBean.getCartInfoByCustomerUUID(customerUUID.toString());
    }

    @PostMapping("create-order")
    public OrderResponse createOrderFromShoppingCart(@RequestHeader(name = "customer-uuid") UUID customerUUID) {
        return gatewayBean.createOrderFromShoppingCart(customerUUID.toString());
    }

    private void customerActivityEvent(String userActivity) {
        kafkaTemplate.send("tracking-customer-activity", userActivity);
    }
}

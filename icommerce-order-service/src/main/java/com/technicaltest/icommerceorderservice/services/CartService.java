package com.technicaltest.icommerceorderservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.technicaltest.icommerceorderservice.bean.CartServiceBean;
import com.technicaltest.icommerceorderservice.bean.OrderServiceBean;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.dto.ProductResult;
import com.technicaltest.icommerceorderservice.events.OrderKafkaListener;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;
import com.technicaltest.icommerceorderservice.support.HeaderGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cart-service")
@RequiredArgsConstructor
public class CartService {
    private final Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    CartServiceBean cartServiceBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Cart service";
    }

    @PostMapping(value = "/add-cart")
    public ResponseEntity<ShoppingCart> addCart(@RequestHeader(value = "userUUID") String userUUID,
                                                @RequestBody CartItem item){
        ShoppingCart cart =cartServiceBean.addItemToCart(userUUID, item);
        if(cart != null) {
            return new ResponseEntity<ShoppingCart>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<ShoppingCart>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cart")
    public ResponseEntity<ShoppingCart> getCart(@RequestHeader(value = "userUUID") String userUUID){
        ShoppingCart cart = cartServiceBean.getCart(userUUID);
        if(cart != null) {
            return new ResponseEntity<ShoppingCart>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.FOUND);
        }
        return new ResponseEntity<ShoppingCart>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create-order")
    public OrderResponse createOrder(@RequestHeader(value = "userUUID") String userUUID) throws JsonProcessingException {
        return cartServiceBean.createOrderFromCart(userUUID);
    }

    @GetMapping(value = "/fetchProductDetailByProductCode")
    public ProductResult fetchProductDetailByProductCode(@RequestParam(name = "codes") List<String> productCodes) throws JsonProcessingException {
        logger.info("fetchProductDetailByProductCode OK with params: ");
        ObjectMapper mapper = new ObjectMapper();
        logger.info(mapper.writeValueAsString(productCodes));
        ProductResult products = cartServiceBean.fetchProductDetailByProductCode(productCodes);
        logger.info("result from cart service to order service");
        logger.info(mapper.writeValueAsString(products));
        return products;
    }
//
//    @PostMapping(value = "/cart", params = {"productId", "quantity"})
//    public ResponseEntity<List<Object>> addItemToCart(
//            @RequestParam("productId") Long productId,
//            @RequestParam("quantity") Integer quantity,
//            @RequestHeader(value = "Cookie") String cartId,
//            HttpServletRequest request) {
//        List<Object> cart = cartServiceBean.getCart(cartId);
////        if(cart != null) {
////            if(cart.isEmpty()){
////                cartServiceBean.addItemToCart(cartId, productId, quantity);
////            }else{
////                if(cartServiceBean.checkIfItemIsExist(cartId, productId)){
////                    cartServiceBean.changeItemQuantity(cartId, productId, quantity);
////                }else {
////                    cartServiceBean.addItemToCart(cartId, productId, quantity);
////                }
////            }
////            return new ResponseEntity<List<Object>>(
////                    cart,
////                    headerGenerator.getHeadersForSuccessPostMethod(request, Long.parseLong(cartId)),
////                    HttpStatus.CREATED);
////        }
//        return new ResponseEntity<List<Object>>(
//                headerGenerator.getHeadersForError(),
//                HttpStatus.BAD_REQUEST);
//    }

//    @DeleteMapping(value = "/cart", params = "productId")
//    public ResponseEntity<Void> removeItemFromCart(
//            @RequestParam("productId") Long productId,
//            @RequestHeader(value = "Cookie") String cartId){
//        List<Object> cart = cartServiceBean.getCart(cartId);
////        if(cart != null) {
////            cartServiceBean.deleteItemFromCart(cartId, productId);
////            return new ResponseEntity<Void>(
////                    headerGenerator.getHeadersForSuccessGetMethod(),
////                    HttpStatus.OK);
////        }
//        return new ResponseEntity<Void>(
//                headerGenerator.getHeadersForError(),
//                HttpStatus.NOT_FOUND);
//    }
}

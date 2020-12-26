package com.technicaltest.icommerceorderservice.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicaltest.icommerceorderservice.client.ProductServiceClient;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartItem;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.CartRedisRepository;
import com.technicaltest.icommerceorderservice.redis_shopping_cart.ShoppingCart;
import com.technicaltest.icommerceorderservice.services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceBeanImpl implements CartServiceBean {
    private final Logger logger = LoggerFactory.getLogger(CartServiceBeanImpl.class);
    @Autowired
    private CartRedisRepository cartRedisRepository;

    @Autowired
    private OrderServiceBean orderServiceBean;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public ShoppingCart addItemToCart(String userUUID, CartItem product) {

        ShoppingCart shoppingCart = getCart(userUUID);
        if (shoppingCart == null){
            List<CartItem> items = new ArrayList<>();
            items.add(product);
            shoppingCart = new ShoppingCart(userUUID, items);
        } else {
            shoppingCart.getProductsInCart().add(product);
        }
        cartRedisRepository.add(shoppingCart);
        return cartRedisRepository.findCart(userUUID);
    }

    @Override
    public ShoppingCart getCart(String userUuId) {
        return cartRedisRepository.findCart(userUuId);
    }

    @Override
    public Object createOrderFromCart(String userUUID) {
        ShoppingCart cart = getCart(userUUID);
        if (cart == null){
            return null;
        }

        return null;
    }

    @Override
    public Object fetchProductDetailByProductCode(List<String> productCodes) throws JsonProcessingException {
        logger.info("fetchProductDetailByProductCode BEAN");
        ObjectMapper mapper = new ObjectMapper();
        logger.info(mapper.writeValueAsString(productCodes));
        return productServiceClient.getProductDetail(productCodes);
    }


//    @Override
//    public void deleteItemFromCart(String cartId, UUID productUuid) {
//        List<TProduct> cart = (List) cartRedisRepository.getCart(cartId, TProduct.class);
//        for(TProduct item : cart){
//            if((item.getUuid()).equals(productUuid)){
//                cartRedisRepository.deleteItemFromCart(cartId, item);
//            }
//        }
//    }
//
//    @Override
//    public boolean checkIfItemIsExist(String cartId, UUID productUuid) {
//        List<TProduct> cart = (List) cartRedisRepository.getCart(cartId, TProduct.class);
//        for(TProduct item : cart){
//            if((item.getUuid()).equals(productUuid)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public List<TProduct> getAllItemsFromCart(String cartId) {
//        List<TProduct> items = (List)cartRedisRepository.getCart(cartId, TProduct.class);
//        return items;
//    }
//
//    @Override
//    public void deleteCart(String cartId) {
//        cartRedisRepository.deleteCart(cartId);
//    }
}

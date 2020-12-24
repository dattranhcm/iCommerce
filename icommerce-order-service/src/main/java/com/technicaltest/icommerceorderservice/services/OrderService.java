package com.technicaltest.icommerceorderservice.services;

import com.technicaltest.icommerceorderservice.bean.OrderServiceBean;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;
import com.technicaltest.icommerceorderservice.support.HTTPDataHelper;
import com.technicaltest.icommerceorderservice.support.HeaderGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderServiceBean orderServiceBean;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping("/welcome")
    public String getAllUsers() {
        return "Welcome to Order service";
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> addActivity() {
        OrderResponse orderResponse = orderServiceBean.createOrder(null);
        return new ResponseEntity<OrderResponse>(
                orderResponse,
                headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.CREATED);
    }

    @GetMapping("/order-detail/{id}")
    public List<TOrder> getOrderDetail(@PathVariable(value = "uuid") UUID orderUuid) {
        return orderServiceBean.findOrderByUuid(orderUuid);
    }
}

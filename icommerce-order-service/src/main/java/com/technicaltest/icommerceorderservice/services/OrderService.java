package com.technicaltest.icommerceorderservice.services;

import com.technicaltest.icommerceorderservice.bean.OrderServiceBean;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * @Description
 * This API allow view order detail of customer
 * @Author Dat Tran
 */
@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderServiceBean orderServiceBean;

    @GetMapping("/health")
    public String getAllUsers() {
        return "Order service is ready now";
    }

    @GetMapping("/order-detail/{uuid}")
    public OrderResponse getOrderDetailByOrderUUID(@PathVariable(value = "uuid") UUID orderUuid) {
        return orderServiceBean.findOrderByUuid(orderUuid);
    }

    @GetMapping("/order-detail-by-customer/{customerUUID}")
    public OrderResponse getOrderDetailByCustomerUUID(@PathVariable(value = "customerUUID") UUID customerUUID) {
        return orderServiceBean.findOrderByCustomerUuid(customerUUID);
    }
}

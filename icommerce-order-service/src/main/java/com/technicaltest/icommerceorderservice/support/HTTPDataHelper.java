package com.technicaltest.icommerceorderservice.support;


import com.technicaltest.icommerceorderservice.dto.CommonResponseBody;
import com.technicaltest.icommerceorderservice.dto.OrderResponse;
import com.technicaltest.icommerceorderservice.entity.TOrder;

public class HTTPDataHelper {

    public static CommonResponseBody internalErrors() {
        return new CommonResponseBody(-1, "Internal error, please check the system log!", "");
    }

    public static CommonResponseBody paramsInvalid(String fields) {
       return new CommonResponseBody(-1, String.format("Missing params %s", fields), "");
    }

    public static CommonResponseBody createSuccess() {
        return new CommonResponseBody(0, "Create success", "");
    }

    public static OrderResponse orderResponse(TOrder order) {
        return new OrderResponse(0, "Order created", order);
    }
}

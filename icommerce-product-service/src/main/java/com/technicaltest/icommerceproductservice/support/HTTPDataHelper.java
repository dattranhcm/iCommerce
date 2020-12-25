package com.technicaltest.icommerceproductservice.support;

import com.technicaltest.icommerceproductservice.dto.CommonResponseBody;
import com.technicaltest.icommerceproductservice.dto.ProductResponse;
import com.technicaltest.icommerceproductservice.entity.TProduct;

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
}

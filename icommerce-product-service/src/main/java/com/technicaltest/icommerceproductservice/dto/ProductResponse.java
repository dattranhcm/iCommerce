package com.technicaltest.icommerceproductservice.dto;

import com.technicaltest.icommerceproductservice.entity.TProduct;

import java.util.List;

public class ProductResponse extends CommonResponseBody{
    private Object data;

    public ProductResponse(Integer code, String content, Object data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setData(data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

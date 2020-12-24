package com.technicaltest.icommerceorderservice.dto;

import com.technicaltest.icommerceorderservice.entity.TOrder;

public class OrderResponse extends CommonResponseBody {
    private TOrder data;

    public OrderResponse(Integer code, String content, TOrder data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setData(data);
    }

    public TOrder getData() {
        return data;
    }

    public void setData(TOrder data) {
        this.data = data;
    }
}

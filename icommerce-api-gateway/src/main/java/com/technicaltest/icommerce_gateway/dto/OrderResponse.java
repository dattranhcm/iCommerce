package com.technicaltest.icommerce_gateway.dto;

public class OrderResponse extends CommonResponseBody {
    private Object data;

    public OrderResponse(Integer code, String content, Object data) {
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

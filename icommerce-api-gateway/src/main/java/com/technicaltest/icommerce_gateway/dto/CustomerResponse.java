package com.technicaltest.icommerce_gateway.dto;

public class CustomerResponse extends CommonResponseBody {
    private CustomerInfo data;
    public CustomerResponse(){}
    public CustomerResponse(Integer code, String content, String message, CustomerInfo data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setMessage(message);
        this.data = data;
    }

    public CustomerInfo getData() {
        return data;
    }

    public void setData(CustomerInfo data) {
        this.data = data;
    }
}

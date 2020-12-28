package com.technicaltest.icommerce_gateway.dto;

public class RegistrationResponse extends CommonResponseBody {
    private String data;
    public RegistrationResponse(){}
    public RegistrationResponse(Integer code, String content, String message, String data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setMessage(message);
        this.data = data;
    }
}

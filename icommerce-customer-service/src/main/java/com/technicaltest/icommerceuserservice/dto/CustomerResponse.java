package com.technicaltest.icommerceuserservice.dto;

public class CustomerResponse extends CommonResponseBody {
    private String data;
    public CustomerResponse(){}
    public CustomerResponse(Integer code, String content, String message, String data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setMessage(message);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

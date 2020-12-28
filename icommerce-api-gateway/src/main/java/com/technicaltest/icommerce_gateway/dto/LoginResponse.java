package com.technicaltest.icommerce_gateway.dto;

public class LoginResponse extends CommonResponseBody {
    private String jwt;
    public LoginResponse(){}
    public LoginResponse(Integer code, String content, String message, String data) {
        super();
        this.setCode(code);
        this.setContent(content);
        this.setMessage(message);
        this.jwt = data;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

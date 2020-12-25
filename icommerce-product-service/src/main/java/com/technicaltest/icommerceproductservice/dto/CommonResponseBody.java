package com.technicaltest.icommerceproductservice.dto;

public class CommonResponseBody {
    private Integer code;
    private String message;
    private String content;

    public CommonResponseBody(){}

    public CommonResponseBody(Integer code, String message, String content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

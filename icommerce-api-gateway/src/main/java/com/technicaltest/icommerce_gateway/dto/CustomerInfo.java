package com.technicaltest.icommerce_gateway.dto;

public class CustomerInfo {
    private String name;
    private String address;
    private String facebookId;
    private String type;

    public CustomerInfo() {}
    public CustomerInfo(String name, String address, String facebookId, String type) {
        this.name = name;
        this.address = address;
        this.facebookId = facebookId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

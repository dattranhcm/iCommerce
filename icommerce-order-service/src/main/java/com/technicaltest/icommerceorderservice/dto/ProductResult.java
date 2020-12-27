package com.technicaltest.icommerceorderservice.dto;

import java.util.List;

public class ProductResult extends CommonResponseBody{

    private List<ProductDto> data;

    public List<ProductDto> getData() {
        return data;
    }

    public void setData(List<ProductDto> data) {
        this.data = data;
    }
}

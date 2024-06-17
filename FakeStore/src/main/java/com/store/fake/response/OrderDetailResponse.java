package com.store.fake.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetailResponse {

    private List<CartResponse> products;

    public List<CartResponse> getProducts() {
        return products;
    }

    public void setProducts(List<CartResponse> products) {
        this.products = products;
    }
}

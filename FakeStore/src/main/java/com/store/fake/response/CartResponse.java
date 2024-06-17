package com.store.fake.response;


import lombok.Data;

@Data
public class CartResponse {
    private Long idOrderDetail;
    private Long idProduct;
    private int amount;

    public CartResponse(Long idOrderDetail, Long idProduct, int amount) {
        this.idOrderDetail = idOrderDetail;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public Long getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

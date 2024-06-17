package com.store.fake.response;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Long idClient;
    private  String email;
    private String username;
    private OrderDetailResponse orderDetail;
    private BigDecimal purchaseamount;
    private int status;
    private String confirmationnumber;
    private LocalDateTime paymentdate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OrderDetailResponse getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailResponse orderDetail) {
        this.orderDetail = orderDetail;
    }

    public BigDecimal getPurchaseamount() {
        return purchaseamount;
    }

    public void setPurchaseamount(BigDecimal purchaseamount) {
        this.purchaseamount = purchaseamount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getConfirmationnumber() {
        return confirmationnumber;
    }

    public void setConfirmationnumber(String confirmationnumber) {
        this.confirmationnumber = confirmationnumber;
    }

    public LocalDateTime getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(LocalDateTime paymentdate) {
        this.paymentdate = paymentdate;
    }
}

package com.store.fake.domain;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "TblOrderPayment")
public class OrderPaymentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOrderPayment", nullable = false)
    private  Long idOrderPayment;

    @Column(name = "IdOrder", nullable = false)
    private  Long idOrder;

    @Column(name = "Purchaseamount", nullable = true)
    private BigDecimal purchaseamount;

    @Column(name = "Confirmationnumber", nullable = false)
    private  String confirmationnumber;

    @Column(name = "Paymentdate", nullable = false)
    private LocalDateTime paymentdate;

    @Column(name = "Status", nullable = false)
    private  int status;

    @ManyToOne(cascade=CascadeType.ALL, targetEntity=OrderDomain.class)
    @JoinColumn(name = "IdOrder", referencedColumnName="IdOrder",insertable = false, updatable = false)
    private OrderDomain order;

    public Long getIdOrderPayment() {
        return idOrderPayment;
    }

    public void setIdOrderPayment(Long idOrderPayment) {
        this.idOrderPayment = idOrderPayment;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public BigDecimal getPurchaseamount() {
        return purchaseamount;
    }

    public void setPurchaseamount(BigDecimal purchaseamount) {
        this.purchaseamount = purchaseamount;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderDomain getOrder() {
        return order;
    }

    public void setOrder(OrderDomain order) {
        this.order = order;
    }
}

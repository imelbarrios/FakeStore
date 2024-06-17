package com.store.fake.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TblOrderDetail")
public class OrderDetailDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOrderdetail", nullable = false)
    private  Long idOrderdetail;

    @Column(name = "IdOrder", nullable = false)
    private  Long idOrder;

    @Column(name = "IdProduct", nullable = false)
    private  Long idProduct;

    @Column(name = "Amount", nullable = false)
    private  int amount;

    @Column(name = "Status", nullable = false)
    private  int status;

    @ManyToOne(cascade=CascadeType.ALL, targetEntity=OrderDomain.class)
    @JoinColumn(name = "IdOrder", referencedColumnName="IdOrder",insertable = false, updatable = false)
    private OrderDomain order;

    public Long getIdOrderdetail() {
        return idOrderdetail;
    }

    public void setIdOrderdetail(Long idOrderdetail) {
        this.idOrderdetail = idOrderdetail;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
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

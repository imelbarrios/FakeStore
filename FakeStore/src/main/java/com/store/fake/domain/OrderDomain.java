package com.store.fake.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "TblOrder")
public class OrderDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOrder", nullable = false)
    private  Long idOrder;

    @Column(name = "IdClient", nullable = false)
    private  Long idClient;

    @Column(name = "Orderdate", nullable = false)
    private LocalDateTime orderdate;

    @Column(name = "Status", nullable = false)
    private  int status;

    @ManyToOne(cascade=CascadeType.ALL, targetEntity=ClientDomain.class)
    @JoinColumn(name = "IdClient", referencedColumnName="IdClient",insertable = false, updatable = false)
    private ClientDomain client;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public LocalDateTime getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDateTime orderdate) {
        this.orderdate = orderdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ClientDomain getClient() {
        return client;
    }

    public void setClient(ClientDomain client) {
        this.client = client;
    }
}

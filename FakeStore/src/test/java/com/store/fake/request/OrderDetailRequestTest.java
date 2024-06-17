package com.store.fake.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderDetailRequestTest {
    @Test
    public void testGettersAndSetters() {
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();

        // Test idOrderDetail
        Long idOrderDetail = 1L;
        orderDetailRequest.setIdOrderDetail(idOrderDetail);
        assertEquals(idOrderDetail, orderDetailRequest.getIdOrderDetail());

        // Test idProduct
        Long idProduct = 2L;
        orderDetailRequest.setIdProduct(idProduct);
        assertEquals(idProduct, orderDetailRequest.getIdProduct());

        // Test amount
        int amount = 5;
        orderDetailRequest.setAmount(amount);
        assertEquals(amount, orderDetailRequest.getAmount());
    }

}

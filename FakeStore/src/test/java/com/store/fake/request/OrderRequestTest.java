package com.store.fake.request;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderRequestTest {
    @Test
    public void testGetIdOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdOrder(1L);
        assertEquals(1L, orderRequest.getIdOrder());
    }

    @Test
    public void testSetIdOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdOrder(2L);
        assertEquals(2L, orderRequest.getIdOrder());
    }

    @Test
    public void testGetIdClient() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdClient(3L);
        assertEquals(3L, orderRequest.getIdClient());
    }

    @Test
    public void testSetIdClient() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setIdClient(4L);
        assertEquals(4L, orderRequest.getIdClient());
    }

    @Test
    public void testGetOrderDetailRequests() {
        OrderRequest orderRequest = new OrderRequest();
        OrderDetailRequest detail1 = new OrderDetailRequest();
        OrderDetailRequest detail2 = new OrderDetailRequest();
        List<OrderDetailRequest> details = Arrays.asList(detail1, detail2);
        orderRequest.setOrderDetailRequests(details);
        assertEquals(details, orderRequest.getOrderDetailRequests());
    }

    @Test
    public void testSetOrderDetailRequests() {
        OrderRequest orderRequest = new OrderRequest();
        OrderDetailRequest detail1 = new OrderDetailRequest();
        OrderDetailRequest detail2 = new OrderDetailRequest();
        List<OrderDetailRequest> details = Arrays.asList(detail1, detail2);
        orderRequest.setOrderDetailRequests(details);
        assertEquals(details, orderRequest.getOrderDetailRequests());
    }
}

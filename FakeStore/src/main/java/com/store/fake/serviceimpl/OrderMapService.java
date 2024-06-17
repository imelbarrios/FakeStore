package com.store.fake.serviceimpl;

import com.store.fake.domain.OrderDetailDomain;
import com.store.fake.domain.OrderDomain;
import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.response.CartResponse;
import com.store.fake.response.OrderDetailResponse;
import com.store.fake.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapService {


    public OrderResponse mapOrderResponse(OrderDomain orderDomain, List<OrderDetailDomain> orderDetailDomain, OrderPaymentDomain orderPaymentDomain ){
        OrderResponse order = new OrderResponse();
        order.setId(orderDomain.getIdOrder());
        order.setIdClient(orderDomain.getClient().getIdClient());
        order.setEmail(orderDomain.getClient().getEmail());


        List<CartResponse> cartResponseList = orderDetailDomain.stream()
                .map(orderDetailResult -> new CartResponse(orderDetailResult.getIdOrderdetail(), orderDetailResult.getIdProduct(), orderDetailResult.getAmount()))
                .collect(Collectors.toList());

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
        orderDetailResponse.setProducts(cartResponseList);

        order.setOrderDetail(orderDetailResponse);
        order.setPurchaseamount(orderPaymentDomain.getPurchaseamount());
        order.setStatus(orderDomain.getStatus());
        order.setConfirmationnumber(orderPaymentDomain.getConfirmationnumber());
        order.setPaymentdate(orderPaymentDomain.getPaymentdate());



        return order;
    }
}

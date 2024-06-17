package com.store.fake.serviceimpl;

import com.store.fake.controller.ProcessPurchaseController;
import com.store.fake.domain.ClientDomain;
import com.store.fake.domain.OrderDetailDomain;
import com.store.fake.domain.OrderDomain;
import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.response.CartResponse;
import com.store.fake.response.ClientResponse;
import com.store.fake.response.OrderDetailResponse;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapService {

    private static final Logger logger = LoggerFactory.getLogger(OrderMapService.class);
    private final IClientService clientService;

    public OrderMapService(IClientService clientService) {
        this.clientService = clientService;
    }

    public OrderResponse mapOrderResponse(OrderDomain orderDomain, List<OrderDetailDomain> orderDetailDomain, OrderPaymentDomain orderPaymentDomain ){

        OrderResponse order = new OrderResponse();
        order.setId(orderDomain.getIdOrder());
        ClientResponse clientDomain = clientService.getFindById(orderDomain.getIdClient());

        order.setIdClient(clientDomain.getId());
        order.setEmail(clientDomain.getEmail());


        if(orderDetailDomain !=null){
            List<CartResponse> cartResponseList = orderDetailDomain.stream()
                    .map(orderDetailResult -> new CartResponse(orderDetailResult.getIdOrderdetail(), orderDetailResult.getIdProduct(), orderDetailResult.getAmount()))
                    .collect(Collectors.toList());

            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            orderDetailResponse.setProducts(cartResponseList);

            order.setOrderDetail(orderDetailResponse);
        }
        if(orderPaymentDomain !=null){
            order.setPurchaseamount(orderPaymentDomain.getPurchaseamount());
            order.setStatus(orderDomain.getStatus());
            order.setConfirmationnumber(orderPaymentDomain.getConfirmationnumber());
            order.setPaymentdate(orderPaymentDomain.getPaymentdate());
        }




        return order;
    }
}

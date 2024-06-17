package com.store.fake.service;

import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.request.OrderDetailRequest;
import com.store.fake.request.OrderRequest;
import com.store.fake.response.OrderDetailResponse;
import com.store.fake.response.OrderResponse;

import java.util.List;


public interface IProcessPurchaseService {
    OrderResponse saveOrder(OrderRequest orderRequest);
    OrderResponse saveOrderDetail(List<OrderDetailRequest> orderRequest, Long idOrder);
    OrderResponse processOrder( OrderRequest orderRequest);
}

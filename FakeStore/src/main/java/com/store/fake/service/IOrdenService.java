package com.store.fake.service;

import com.store.fake.response.OrderDetailResponse;
import com.store.fake.response.OrderResponse;

import java.util.List;

public interface IOrdenService {

    OrderResponse getOrder(Long idOrder);
    List<OrderResponse> getOrderByClient(Long id);
    List<OrderResponse> getAllOrder();


}

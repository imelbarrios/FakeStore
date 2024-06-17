package com.store.fake.serviceimpl;

import com.store.fake.domain.OrderDetailDomain;
import com.store.fake.domain.OrderDomain;
import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.repository.IOrderDetailRepository;
import com.store.fake.repository.IOrderPaymentRepository;
import com.store.fake.repository.IOrderRepository;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IOrdenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrdenService {

    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderPaymentRepository orderPaymentRepository;
    private final OrderMapService orderMapService;

    public OrderServiceImpl(IOrderRepository orderRepository, IOrderDetailRepository orderDetailRepository, IOrderPaymentRepository orderPaymentRepository, OrderMapService orderMapService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderPaymentRepository = orderPaymentRepository;
        this.orderMapService = orderMapService;
    }


    @Override
    public OrderResponse getOrder(Long idOrder) {
        OrderDomain orderDomain = orderRepository.findByRecordId(idOrder);
        List<OrderDetailDomain> orderDetailDomains = orderDetailRepository.findAllByIdOrder(idOrder);
        OrderPaymentDomain orderPaymentDomain = orderPaymentRepository.findByIdOrder(idOrder);

        return orderMapService.mapOrderResponse(orderDomain,orderDetailDomains,orderPaymentDomain);
    }


    @Override
    public List<OrderResponse> getOrderByClient(Long id) {
        try {
            List<OrderDomain> orderDomainList = orderRepository.findByIdClient(id);
            List<OrderResponse> orderResponses = new ArrayList<>();

            for(OrderDomain orderDomain : orderDomainList){
                OrderResponse orderResponse = getOrder(orderDomain.getIdOrder());
                orderResponses.add(orderResponse);
            }

            return orderResponses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<OrderResponse> getAllOrder() {
        try {
            List<OrderDomain> orderDomainList = orderRepository.findAll();
            List<OrderResponse> orderResponses = new ArrayList<>();

            for(OrderDomain orderDomain : orderDomainList){
                OrderResponse orderResponse = getOrder(orderDomain.getIdOrder());
                orderResponses.add(orderResponse);
            }

            return orderResponses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

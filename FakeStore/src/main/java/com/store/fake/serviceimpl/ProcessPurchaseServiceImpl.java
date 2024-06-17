package com.store.fake.serviceimpl;


import com.store.fake.domain.OrderDetailDomain;
import com.store.fake.domain.OrderDomain;
import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.repository.IOrderDetailRepository;
import com.store.fake.repository.IOrderPaymentRepository;
import com.store.fake.repository.IOrderRepository;
import com.store.fake.request.OrderDetailRequest;
import com.store.fake.request.OrderRequest;
import com.store.fake.response.ClientResponse;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IClientService;
import com.store.fake.service.IProcessPurchaseService;
import com.store.fake.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessPurchaseServiceImpl implements IProcessPurchaseService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessPurchaseServiceImpl.class);


    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IOrderPaymentRepository orderPaymentRepository;
    private final IClientService clientService;
    private final IProductService productService;
    private final PaymentConfirmationService paymentConfirmationService;
    private final OrderMapService orderMapService;

    public ProcessPurchaseServiceImpl(IOrderRepository orderRepository, IOrderDetailRepository orderDetailRepository, IOrderPaymentRepository orderPaymentRepository, IClientService clientService, IProductService productService, PaymentConfirmationService paymentConfirmationService, OrderMapService orderMapService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderPaymentRepository = orderPaymentRepository;
        this.clientService = clientService;
        this.productService = productService;
        this.paymentConfirmationService = paymentConfirmationService;
        this.orderMapService = orderMapService;
    }

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setIdClient(orderRequest.getIdClient());
        orderDomain.setOrderdate(LocalDateTime.now());
        orderDomain.setStatus(1);
        OrderDomain order = orderRepository.saveAndFlush(orderDomain);

        logger.info("idOrder is :{}", order.getIdOrder());
        OrderDomain orderConfirmation = orderRepository.findByRecordId(order.getIdOrder());
        logger.info("Order Information Create:{}", orderConfirmation.toString());

        return orderMapService.mapOrderResponse(orderConfirmation,null,null);
    }

    @Override
    public OrderResponse saveOrderDetail(List<OrderDetailRequest> orderRequest, Long idOrder) {

        List<OrderDetailDomain> orderDetailDomainSave  = new ArrayList<>();

        for(OrderDetailRequest order: orderRequest){
            OrderDetailDomain orderDetail = new OrderDetailDomain();
            orderDetail.setIdOrder(idOrder);
            orderDetail.setIdProduct(order.getIdProduct());
            orderDetail.setAmount(order.getAmount());
            orderDetail.setStatus(1);
            orderDetailDomainSave.add(orderDetail);

        }

        orderDetailRepository.saveAllAndFlush(orderDetailDomainSave);

        OrderDomain orderDomain = orderRepository.findByRecordId(idOrder);
        List<OrderDetailDomain> orderDetailDomain1 = orderDetailRepository.findAllByIdOrder(idOrder);

        logger.info("Order Information :{}", orderDomain.toString());

        logger.info("Order Detail Information :{}", orderDetailDomain1.toString());

        return orderMapService.mapOrderResponse(orderDomain,orderDetailDomain1,null);
    }

    @Override
    public OrderResponse processOrder(OrderRequest orderRequest) {
        OrderDomain orderDomain = orderRepository.findByRecordId(orderRequest.getIdOrder());
        List<OrderDetailDomain> orderDetailDomain = orderDetailRepository.findAllByIdOrder(orderRequest.getIdOrder());

        OrderPaymentDomain orderPayment = new OrderPaymentDomain();
        orderPayment.setIdOrder(orderRequest.getIdOrder());
        orderPayment.setPurchaseamount(new BigDecimal(0.0));
        orderPayment.setConfirmationnumber(paymentConfirmationService.generatePaymentConfirmation());
        orderPayment.setPaymentdate(LocalDateTime.now());
        orderPayment.setStatus(2);
        OrderPaymentDomain orderPaymentDomain = orderPaymentRepository.save(orderPayment);



        return orderMapService.mapOrderResponse(orderDomain, orderDetailDomain, orderPaymentDomain);
    }


}

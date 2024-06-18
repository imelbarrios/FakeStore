package com.store.fake.serviceimpl;

import com.store.fake.domain.OrderDetailDomain;
import com.store.fake.domain.OrderDomain;
import com.store.fake.domain.OrderPaymentDomain;
import com.store.fake.repository.IOrderDetailRepository;
import com.store.fake.repository.IOrderPaymentRepository;
import com.store.fake.repository.IOrderRepository;
import com.store.fake.response.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IOrderDetailRepository orderDetailRepository;

    @Mock
    private IOrderPaymentRepository orderPaymentRepository;

    @Mock
    private OrderMapService orderMapService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetOrder() {
        Long idOrder = 1L;
        OrderDomain orderDomain = new OrderDomain();
        List<OrderDetailDomain> orderDetailDomains = Collections.singletonList(new OrderDetailDomain());
        OrderPaymentDomain orderPaymentDomain = new OrderPaymentDomain();
        OrderResponse expectedResponse = new OrderResponse();

        when(orderRepository.findByRecordId(idOrder)).thenReturn(orderDomain);
        when(orderDetailRepository.findAllByIdOrder(idOrder)).thenReturn(orderDetailDomains);
        when(orderPaymentRepository.findByIdOrder(idOrder)).thenReturn(orderPaymentDomain);
        when(orderMapService.mapOrderResponse(orderDomain, orderDetailDomains, orderPaymentDomain)).thenReturn(expectedResponse);

        OrderResponse actualResponse = orderService.getOrder(idOrder);

        assertEquals(expectedResponse, actualResponse);
        verify(orderRepository).findByRecordId(idOrder);
        verify(orderDetailRepository).findAllByIdOrder(idOrder);
        verify(orderPaymentRepository).findByIdOrder(idOrder);
        verify(orderMapService).mapOrderResponse(orderDomain, orderDetailDomains, orderPaymentDomain);
    }

    @Test
    public void testGetOrderByClient() {
        Long idClient = 1L;
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setIdOrder(1L);
        List<OrderDomain> orderDomainList = Collections.singletonList(orderDomain);
        OrderResponse orderResponse = new OrderResponse();

        when(orderRepository.findByIdClient(idClient)).thenReturn(orderDomainList);
        when(orderService.getOrder(orderDomain.getIdOrder())).thenReturn(orderResponse);

        List<OrderResponse> actualResponses = orderService.getOrderByClient(idClient);

        assertEquals(1, actualResponses.size());
        assertEquals(orderResponse, actualResponses.get(0));
    }

    @Test
    public void testGetOrderByClient_Exception() {
        Long idClient = 1L;

        when(orderRepository.findByIdClient(idClient)).thenThrow(new RuntimeException("Exception"));

        assertThrows(RuntimeException.class, () -> orderService.getOrderByClient(idClient));
        verify(orderRepository).findByIdClient(idClient);
    }

    @Test
    public void testGetAllOrder() {
        OrderDomain orderDomain = new OrderDomain();
        orderDomain.setIdOrder(1L);
        List<OrderDomain> orderDomainList = Collections.singletonList(orderDomain);
        OrderResponse orderResponse = new OrderResponse();

        when(orderRepository.findAll()).thenReturn(orderDomainList);
        when(orderService.getOrder(orderDomain.getIdOrder())).thenReturn(orderResponse);

        List<OrderResponse> actualResponses = orderService.getAllOrder();

        assertEquals(1, actualResponses.size());
        assertEquals(orderResponse, actualResponses.get(0));

    }

    @Test
    public void testGetAllOrder_Exception() {
        when(orderRepository.findAll()).thenThrow(new RuntimeException("Exception"));

        assertThrows(RuntimeException.class, () -> orderService.getAllOrder());

    }

}

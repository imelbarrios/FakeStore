package com.store.fake.controller;

import com.store.fake.request.OrderRequest;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IProcessPurchaseService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import com.store.fake.utils.ValidateModel;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProcessPurchaseControllerTest {

    @Mock
    private IProcessPurchaseService processPurchaseService;

    @Mock
    private ValidateModel validate;

    @Mock
    private HttpServletRequest req;

    @Mock
    private BindingResult result;

    @InjectMocks
    private ProcessPurchaseController processPurchaseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 /*   @Test
    public void testCreateOrder_Success() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(1L);

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(null);
        when(processPurchaseService.saveOrder(any(OrderRequest.class))).thenReturn(orderResponse);
        when(processPurchaseService.saveOrderDetail(anyList(), anyLong())).thenReturn(orderResponse);
        when(Autorizations.getResponseSuccess(any(), anyString())).thenReturn(ResponseEntity.ok(new GeneralResponse()));

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrder(req, orderRequest, result);

        assertEquals(ResponseEntity.ok(new GeneralResponse()), response);
        verify(processPurchaseService, times(1)).saveOrder(any(OrderRequest.class));
        verify(processPurchaseService, times(1)).saveOrderDetail(anyList(), anyLong());
    }

    @Test
    public void testCreateOrder_ValidationError() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        ResponseEntity<GeneralResponse> validationErrorResponse = ResponseEntity.badRequest().body(new GeneralResponse());

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(validationErrorResponse);

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrder(req, orderRequest, result);

        assertEquals(validationErrorResponse, response);
        verify(processPurchaseService, times(0)).saveOrder(any(OrderRequest.class));
        verify(processPurchaseService, times(0)).saveOrderDetail(anyList(), anyLong());
    }

    @Test
    public void testCreateOrder_Exception() throws Exception {
        OrderRequest orderRequest = new OrderRequest();

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(null);
        when(processPurchaseService.saveOrder(any(OrderRequest.class))).thenThrow(new RuntimeException("Exception occurred"));
        when(Autorizations.getResponseServerError(anyString())).thenReturn(ResponseEntity.status(500).body(new GeneralResponse()));

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrder(req, orderRequest, result);

        assertEquals(ResponseEntity.status(500).body(new GeneralResponse()), response);
        verify(processPurchaseService, times(1)).saveOrder(any(OrderRequest.class));
        verify(processPurchaseService, times(0)).saveOrderDetail(anyList(), anyLong());
    }

    @Test
    public void testCreateOrderDetail_Success() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        OrderResponse orderResponse = new OrderResponse();

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(null);
        when(processPurchaseService.processOrder(any(OrderRequest.class))).thenReturn(orderResponse);
        when(Autorizations.getResponseSuccess(any(), anyString())).thenReturn(ResponseEntity.ok(new GeneralResponse()));

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrderDetail(req, orderRequest, result);

        assertEquals(ResponseEntity.ok(new GeneralResponse()), response);
        verify(processPurchaseService, times(1)).processOrder(any(OrderRequest.class));
    }

    @Test
    public void testCreateOrderDetail_ValidationError() throws Exception {
        OrderRequest orderRequest = new OrderRequest();
        ResponseEntity<GeneralResponse> validationErrorResponse = ResponseEntity.badRequest().body(new GeneralResponse());

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(validationErrorResponse);

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrderDetail(req, orderRequest, result);

        assertEquals(validationErrorResponse, response);
        verify(processPurchaseService, times(0)).processOrder(any(OrderRequest.class));
    }

    @Test
    public void testCreateOrderDetail_Exception() throws Exception {
        OrderRequest orderRequest = new OrderRequest();

        when(validate.validateModel(any(OrderRequest.class))).thenReturn(null);
        when(processPurchaseService.processOrder(any(OrderRequest.class))).thenThrow(new RuntimeException("Exception occurred"));
        when(Autorizations.getResponseServerError(anyString())).thenReturn(ResponseEntity.status(500).body(new GeneralResponse()));

        ResponseEntity<GeneralResponse> response = processPurchaseController.createOrderDetail(req, orderRequest, result);

        assertEquals(ResponseEntity.status(500).body(new GeneralResponse()), response);
        verify(processPurchaseService, times(1)).processOrder(any(OrderRequest.class));
    }
    
  */
}

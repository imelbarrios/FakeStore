package com.store.fake.controller;

import com.store.fake.response.OrderResponse;
import com.store.fake.service.IOrdenService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
public class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IOrdenService ordenService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetOrderById_Success() throws Exception {
        OrderResponse orderResponse = new OrderResponse();
        when(ordenService.getOrder(anyLong())).thenReturn(orderResponse);

        mockMvc.perform(get("/v1/FakeStore/Order/Id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testGetOrderById_NotFound() throws Exception {
        when(ordenService.getOrder(anyLong())).thenReturn(null);

        mockMvc.perform(get("/v1/FakeStore/Order/Id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void testGetOrderById_Exception() throws Exception {
        when(ordenService.getOrder(anyLong())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(get("/v1/FakeStore/Order/Id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(CommonConstants.HTTP_CODE_ERROR_INTERNAL_SERVICE));
    }

    @Test
    public void testGetOrderByIdClient_Success() throws Exception {
        List<OrderResponse> orderResponses = Collections.singletonList(new OrderResponse());
        when(ordenService.getOrderByClient(anyLong())).thenReturn(orderResponses);

        mockMvc.perform(get("/v1/FakeStore/Orders/IdClient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testGetOrderByIdClient_NotFound() throws Exception {
        when(ordenService.getOrderByClient(anyLong())).thenReturn(null);

        mockMvc.perform(get("/v1/FakeStore/Orders/IdClient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void testGetOrderByIdClient_Exception() throws Exception {
        when(ordenService.getOrderByClient(anyLong())).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(get("/v1/FakeStore/Orders/IdClient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(CommonConstants.HTTP_CODE_ERROR_INTERNAL_SERVICE));
    }

    @Test
    public void testGetOrders_Success() throws Exception {
        List<OrderResponse> orderResponses = Collections.singletonList(new OrderResponse());
        when(ordenService.getAllOrder()).thenReturn(orderResponses);

        mockMvc.perform(get("/v1/FakeStore/Orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    public void testGetOrders_NotFound() throws Exception {
        when(ordenService.getAllOrder()).thenReturn(null);

        mockMvc.perform(get("/v1/FakeStore/Orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    public void testGetOrders_Exception() throws Exception {
        when(ordenService.getAllOrder()).thenThrow(new RuntimeException("Error"));

        mockMvc.perform(get("/v1/FakeStore/Orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(CommonConstants.HTTP_CODE_ERROR_INTERNAL_SERVICE));
    }
}

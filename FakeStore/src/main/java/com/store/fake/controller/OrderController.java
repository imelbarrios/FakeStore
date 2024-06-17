package com.store.fake.controller;

import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IOrdenService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@DispatcherServletCustomConfig.V1APIController
@RestController
public class OrderController {

    ResponseEntity<GeneralResponse> responseOrder = null;

    private final IOrdenService ordenService;

    public OrderController(IOrdenService ordenService) {
        this.ordenService = ordenService;
    }


    @GetMapping("/Order/Id/{id}")
    @ApiResponse(responseCode = "200", description = "Get order by id")
    @Operation(summary = "Get order by id")
    public ResponseEntity<GeneralResponse> getOrderById(HttpServletRequest req, @PathVariable("id") Long id) {
        try {
            OrderResponse order = ordenService.getOrder(id);

            if (order == null) {
                responseOrder = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseOrder = Autorizations.getResponseSuccess(order, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseOrder = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseOrder;
    }

    @GetMapping("/Orders/IdClient/{id}")
    @ApiResponse(responseCode = "200", description = "Get order by customer id")
    @Operation(summary = "Get order by customer id")
    public ResponseEntity<GeneralResponse> getOrderByIdClient(HttpServletRequest req, @PathVariable("id") Long id) {
        try {
            List<OrderResponse> order = ordenService.getOrderByClient(id);

            if (order == null) {
                responseOrder = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseOrder = Autorizations.getResponseSuccess(order, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseOrder = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseOrder;
    }

    @GetMapping("/Orders")
    @ApiResponse(responseCode = "200", description = "Get all orders")
    @Operation(summary = "Get all orders")
    public ResponseEntity<GeneralResponse> getOrders(HttpServletRequest req) {
        try {
            List<OrderResponse> order = ordenService.getAllOrder();

            if (order == null) {
                responseOrder = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseOrder = Autorizations.getResponseSuccess(order, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseOrder = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseOrder;
    }


}

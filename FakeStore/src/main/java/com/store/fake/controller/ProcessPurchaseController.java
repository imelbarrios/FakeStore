package com.store.fake.controller;

import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.request.OrderRequest;
import com.store.fake.response.OrderResponse;
import com.store.fake.service.IProcessPurchaseService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import com.store.fake.utils.ValidateModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@DispatcherServletCustomConfig.V1APIController
@RestController
public class ProcessPurchaseController {

    ResponseEntity<GeneralResponse> responseProcess = null;
    ValidateModel validate = new ValidateModel();

    private final IProcessPurchaseService processPurchaseService;

    public ProcessPurchaseController(IProcessPurchaseService processPurchaseService) {
        this.processPurchaseService = processPurchaseService;
    }


    @PostMapping("/ProcessOrder")
    @ApiResponse(responseCode = "200", description = "API para Guardar actividad economica")
    @Operation(summary = "Guardar actividad economica")
    public ResponseEntity<GeneralResponse> createOrder(HttpServletRequest req,
                                                      @RequestBody OrderRequest orderDto, BindingResult result)
            throws NumberFormatException, SecurityException {

        try {

            responseProcess = validate.validateModel(orderDto);

            if (responseProcess != null) {
                return responseProcess;
            }


            OrderResponse orderResponse = processPurchaseService.saveOrder(orderDto);

            OrderResponse orderProcess = processPurchaseService.saveOrderDetail(orderDto.getOrderDetailRequests(),orderResponse.getId());

            responseProcess = Autorizations.getResponseSuccess(orderProcess, CommonConstants.SUCCESS_ORDER);


        } catch (Exception e) {

            responseProcess = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseProcess;

    }


    @PostMapping("/ProcessOrder/Payment")
    @ApiResponse(responseCode = "200", description = "API para Guardar actividad economica")
    @Operation(summary = "Guardar actividad economica")
    public ResponseEntity<GeneralResponse> createOrderDetail(HttpServletRequest req,
                                                       @RequestBody OrderRequest orderDto, BindingResult result)
            throws NumberFormatException, SecurityException {

        try {

            responseProcess = validate.validateModel(orderDto);

            if (responseProcess != null) {
                return responseProcess;
            }


            OrderResponse orderResponse = processPurchaseService.processOrder(orderDto);

            responseProcess = Autorizations.getResponseSuccess(orderResponse, CommonConstants.SUCCESS_ORDER);


        } catch (Exception e) {

            responseProcess = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseProcess;

    }


}

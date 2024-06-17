package com.store.fake.controller;

import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.response.ProductResponse;
import com.store.fake.service.IProductService;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.CommonConstants;
import com.store.fake.utils.GeneralResponse;
import com.store.fake.utils.ValidateModel;
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
public class ProductController {

    ResponseEntity<GeneralResponse> responseProduct = null;
    ValidateModel validate = new ValidateModel();

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/Products")
    @ApiResponse(responseCode = "200", description = "Get all products")
    @Operation(summary = "Get all products")
    public ResponseEntity<GeneralResponse> getAllProducts(HttpServletRequest req) {
        try {
            List<ProductResponse> products = productService.getAll();

            if (products == null) {
                responseProduct = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseProduct = Autorizations.getResponseSuccess(products, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseProduct = Autorizations.getResponseServerError(e.getMessage());

        }

        return responseProduct;
    }

    @GetMapping("/Products/Category/{type}")
    @ApiResponse(responseCode = "200", description = "Get all products")
    @Operation(summary = "Get all products")
    public ResponseEntity<GeneralResponse> getCategoryProducts(HttpServletRequest req,@PathVariable("type") String type) {
        try {
            List<ProductResponse> products = productService.getCategory(type);

            if (products == null) {
                responseProduct = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseProduct = Autorizations.getResponseSuccess(products, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseProduct = Autorizations.getResponseServerError(e.getMessage());

        }
        return responseProduct;
    }

    @GetMapping("/Products/Id/{id}")
    @ApiResponse(responseCode = "200", description = "Get all products")
    @Operation(summary = "Get all products")
    public ResponseEntity<GeneralResponse> getCategoryProducts(HttpServletRequest req,@PathVariable("id") int id) {
        try {
            ProductResponse product = productService.getIdProduct(id);

            if (product == null) {
                responseProduct = Autorizations.getResponsenBadRequest(null, CommonConstants.NOT_FOUND);
            } else {
                responseProduct = Autorizations.getResponseSuccess(product, CommonConstants.SUCCESS_RESULT);
            }

        } catch (Exception e) {

            responseProduct = Autorizations.getResponseServerError(e.getMessage());

        }
        return responseProduct;
    }



}

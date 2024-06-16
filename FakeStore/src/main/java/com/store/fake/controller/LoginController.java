package com.store.fake.controller;

import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.request.LoginRequest;
import com.store.fake.utils.Autorizations;
import com.store.fake.utils.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@DispatcherServletCustomConfig.V1APIController
@RestController
@RequiredArgsConstructor
public class LoginController {
    ResponseEntity<GeneralResponse> response = null;

    @PostMapping(value = "/Login")
    @ApiResponse(responseCode = "200", description= "Endpoint to log into the application")
    @Operation(summary = "Login to the application")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest userRequest, HttpServletRequest request)
    {


        response = Autorizations.getResponseSuccess(null, CommonConstants.APPROVED_CREDENTIALS);

        return response;
    }
}

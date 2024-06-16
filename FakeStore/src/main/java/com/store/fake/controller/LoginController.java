package com.store.fake.controller;

import com.store.fake.config.DispatcherServletCustomConfig;
import com.store.fake.request.LoginRequest;
import com.store.fake.response.ClientResponse;
import com.store.fake.response.LoginResponse;
import com.store.fake.security.JwtTokenProvider;
import com.store.fake.service.IClientService;
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
    private final IClientService clientService;
    ValidateModel validate = new ValidateModel();
    LoginResponse loginResponse = new LoginResponse();
    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();


    @PostMapping(value = "/Login")
    @ApiResponse(responseCode = "200", description= "Endpoint to log into the application")
    @Operation(summary = "Login to the application")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest userRequest, HttpServletRequest request)
    {

        ClientResponse clientResponse = clientService.getInformation(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());
        if(!clientResponse.getEmail().equals(userRequest.getEmail())){
            response = Autorizations.getResponsenBadRequest(null, CommonConstants.USER_NOT_FOUND);
            return response;
        }
        String token = jwtTokenProvider.createToken(clientResponse.getId(),clientResponse.getUsername(),clientResponse.getLastname());
        String refresh = jwtTokenProvider.refreshToken(clientResponse.getId(),clientResponse.getUsername(),clientResponse.getLastname());

        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refresh);
        loginResponse.setClient(clientResponse);

        response = Autorizations.getResponseSuccess(loginResponse, CommonConstants.APPROVED_CREDENTIALS);

        return response;
    }


}

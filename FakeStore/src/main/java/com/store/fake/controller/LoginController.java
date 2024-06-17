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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@DispatcherServletCustomConfig.V1APIController
@RestController
public class LoginController {
    ResponseEntity<GeneralResponse> response = null;

    ValidateModel validate = new ValidateModel();
    LoginResponse loginResponse = new LoginResponse();
    JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    private final IClientService clientService ;

    public LoginController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/Login")
    @Operation(summary = "Login to the application")
    @ApiResponse(responseCode = "200", description = "Login")
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest userRequest, HttpServletRequest request)
    {
        response = validate.validateModel(userRequest);
        if(response != null) {
            return response;
        }

        ClientResponse clientResponse = clientService.getLogin(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail());

        if(!clientResponse.getUsername().equals(userRequest.getUsername().trim())){
            response = Autorizations.getResponsenBadRequest(null, CommonConstants.USER_NOT_FOUND);
            return response;
        }

        String token = jwtTokenProvider.createToken(Long.valueOf(clientResponse.getId()).intValue(),clientResponse.getUsername(),clientResponse.getLastname());
        String refresh = jwtTokenProvider.refreshToken(Long.valueOf(clientResponse.getId()).intValue(),clientResponse.getUsername(),clientResponse.getLastname());

        loginResponse.setToken(token);
        loginResponse.setRefreshToken(refresh);
        loginResponse.setClient(clientResponse);

        response = Autorizations.getResponseSuccess(loginResponse, CommonConstants.APPROVED_CREDENTIALS);

        return response;
    }

    @GetMapping( value= "/RefreshToken")
    @ApiResponse(responseCode = "200", description= "Generate token")
    @Operation(summary = "Refresh Token")
    public ResponseEntity<GeneralResponse> refreshToken(HttpServletRequest req){

        Long id = Long.valueOf(jwtTokenProvider.getClaims(req,"ID")).longValue();

        ClientResponse client = clientService.getFindById(id);

        String accessToken = jwtTokenProvider.createToken(Long.valueOf(client.getId()).intValue(),client.getUsername(),client.getLastname());

        response = Autorizations.getResponseSuccess(accessToken, CommonConstants.APPROVED_CREDENTIALS);
        return response;
    }


}

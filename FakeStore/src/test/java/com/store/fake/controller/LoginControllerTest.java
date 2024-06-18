package com.store.fake.controller;

import com.store.fake.request.LoginRequest;
import com.store.fake.response.ClientResponse;
import com.store.fake.response.LoginResponse;
import com.store.fake.security.JwtTokenProvider;
import com.store.fake.service.IClientService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    @InjectMocks
    private LoginController loginController;

    @Mock
    private IClientService clientService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private ValidateModel validateModel;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");
        loginRequest.setEmail("test@example.com");

        ClientResponse clientResponse = new ClientResponse(1L,null,"testUser",null,null,"testLast");

        when(validateModel.validateModel(any(LoginRequest.class))).thenReturn(null);
        when(clientService.getLogin(anyString(), anyString(), anyString())).thenReturn(clientResponse);
        when(jwtTokenProvider.createToken(any(Integer.class), anyString(), anyString())).thenReturn("testToken");
        when(jwtTokenProvider.refreshToken(any(Integer.class), anyString(), anyString())).thenReturn("testRefreshToken");

        ResponseEntity<GeneralResponse> response = loginController.login(loginRequest, request);

        assertEquals(200, response.getStatusCodeValue());
        LoginResponse loginResponse = (LoginResponse) response.getBody().getData();
        assertEquals(loginResponse.getToken(), loginResponse.getToken());
        assertEquals(loginResponse.getRefreshToken(), loginResponse.getRefreshToken());
        assertEquals(clientResponse, loginResponse.getClient());
    }

 /*   @Test
    public void testLogin_InvalidModel() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");
        loginRequest.setEmail("test@example.com");

        ResponseEntity<GeneralResponse> invalidResponse = ResponseEntity.badRequest().body(new GeneralResponse());

        when(validateModel.validateModel(any(LoginRequest.class))).thenReturn(invalidResponse);

        ResponseEntity<GeneralResponse> response = loginController.login(loginRequest, request);

        assertEquals(400, response.getStatusCodeValue());
    }*/

    @Test
    public void testLogin_UserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPass");
        loginRequest.setEmail("test@example.com");

        ClientResponse clientResponse = new ClientResponse(1L,null,"testUser",null,"wrongUser","testLast");


        when(validateModel.validateModel(any(LoginRequest.class))).thenReturn(null);
        when(clientService.getLogin(anyString(), anyString(), anyString())).thenReturn(clientResponse);

        ResponseEntity<GeneralResponse> response = loginController.login(loginRequest, request);

        assertEquals(200, response.getStatusCodeValue());
    }

  /*  @Test
    public void testRefreshToken_Success() {
        Long id = 1L;
        ClientResponse clientResponse = new ClientResponse(1L,null,"testUser",null,"wrongUser","testLast");

        when(jwtTokenProvider.getClaims(any(HttpServletRequest.class), anyString())).thenReturn("1");
        when(clientService.getFindById(any(Long.class))).thenReturn(clientResponse);
        when(jwtTokenProvider.createToken(any(Integer.class), anyString(), anyString())).thenReturn("newAccessToken");

        ResponseEntity<GeneralResponse> response = loginController.refreshToken(request);

        assertEquals(200, response.getStatusCodeValue());

    }*/
}

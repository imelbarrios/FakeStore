package com.store.fake.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginResponseTest {
    private LoginResponse loginResponse;

    @BeforeEach
    public void setUp() {
        loginResponse = new LoginResponse();
    }

    @Test
    public void testGetAndSetToken() {
        assertNull(loginResponse.getToken());
        String token = "sampleToken";
        loginResponse.setToken(token);
        assertEquals(token, loginResponse.getToken());
    }

    @Test
    public void testGetAndSetRefreshToken() {
        assertNull(loginResponse.getRefreshToken());
        String refreshToken = "sampleRefreshToken";
        loginResponse.setRefreshToken(refreshToken);
        assertEquals(refreshToken, loginResponse.getRefreshToken());
    }

    @Test
    public void testGetAndSetClient() {
        assertNull(loginResponse.getClient());
        ClientResponse client = new ClientResponse(null,null,null,null,null,null);
        loginResponse.setClient(client);
        assertEquals(client, loginResponse.getClient());
    }
}

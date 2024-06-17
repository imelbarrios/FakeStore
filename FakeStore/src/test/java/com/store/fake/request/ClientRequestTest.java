package com.store.fake.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRequestTest {
    @Test
    void testGettersAndSetters() {
        ClientRequest clientRequest = new ClientRequest();

        // Test idClient
        clientRequest.setIdClient(1L);
        assertEquals(1L, clientRequest.getIdClient());

        // Test email
        clientRequest.setEmail("test@example.com");
        assertEquals("test@example.com", clientRequest.getEmail());

        // Test username
        clientRequest.setUsername("testuser");
        assertEquals("testuser", clientRequest.getUsername());

        // Test password
        clientRequest.setPassword("password123");
        assertEquals("password123", clientRequest.getPassword());

        // Test firstname
        clientRequest.setFirstname("John");
        assertEquals("John", clientRequest.getFirstname());

        // Test lastname
        clientRequest.setLastname("Doe");
        assertEquals("Doe", clientRequest.getLastname());

        // Test phone
        clientRequest.setPhone("1234567890");
        assertEquals("1234567890", clientRequest.getPhone());
    }


}

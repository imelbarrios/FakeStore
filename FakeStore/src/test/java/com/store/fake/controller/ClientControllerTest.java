package com.store.fake.controller;

import com.store.fake.domain.ClientDomain;
import com.store.fake.request.ClientRequest;
import com.store.fake.response.ClientResponse;
import com.store.fake.service.IClientService;
import com.store.fake.utils.GeneralResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientControllerTest {

    @Mock
    private IClientService clientService;

    @Mock
    private HttpServletRequest req;

    @Mock
    private BindingResult result;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClients_Success() {
        List<ClientResponse> clients = new ArrayList<>();
        clients.add(new ClientResponse(null,null,null,null,null,null));
        when(clientService.getAllClient()).thenReturn(clients);

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req);

        assertEquals(200, response.getStatusCodeValue());
        verify(clientService, times(1)).getAllClient();
    }

    @Test
    public void testGetAllClients_NotFound() {
        when(clientService.getAllClient()).thenReturn(null);

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req);

        assertEquals(400, response.getStatusCodeValue());
        verify(clientService, times(1)).getAllClient();
    }

    @Test
    public void testGetAllClients_Exception() {
        when(clientService.getAllClient()).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req);

        assertEquals(500, response.getStatusCodeValue());
        verify(clientService, times(1)).getAllClient();
    }

    @Test
    public void testGetClientById_Success() {
        ClientResponse client = new ClientResponse(1L,null,null,null,null,null);
        when(clientService.getFindById(1L)).thenReturn(client);

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req, 1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(clientService, times(1)).getFindById(1L);
    }

    @Test
    public void testGetClientById_NotFound() {
        when(clientService.getFindById(1L)).thenReturn(null);

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req, 1L);

        assertEquals(400, response.getStatusCodeValue());
        verify(clientService, times(1)).getFindById(1L);
    }

    @Test
    public void testGetClientById_Exception() {
        when(clientService.getFindById(1L)).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = clientController.getAllClients(req, 1L);

        assertEquals(500, response.getStatusCodeValue());
        verify(clientService, times(1)).getFindById(1L);
    }

    @Test
    public void testSaveClient_Success() {
        ClientRequest clientRequest = new ClientRequest();
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        when(clientService.saveClient(any(ClientDomain.class))).thenReturn(1L);
        when(result.hasErrors()).thenReturn(false);

        ResponseEntity<GeneralResponse> response = clientController.saveClient(req, clientRequest, result);

        assertEquals(200, response.getStatusCodeValue());
        verify(clientService, times(1)).saveClient(any(ClientDomain.class));
    }

    @Test
    public void testSaveClient_Exception() {
        ClientRequest clientRequest = new ClientRequest();
        when(clientService.saveClient(any(ClientDomain.class))).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = clientController.saveClient(req, clientRequest, result);

        assertEquals(500, response.getStatusCodeValue());
        verify(clientService, times(1)).saveClient(any(ClientDomain.class));
    }

    @Test
    public void testUpdateClient_Success() {
        ClientRequest clientRequest = new ClientRequest();
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        ClientResponse clientResponse = new ClientResponse(1L,null,null,null,null,null);
        when(clientService.getFindById(1L)).thenReturn(clientResponse);
        when(clientService.saveClient(any(ClientDomain.class))).thenReturn(1L);
        when(result.hasErrors()).thenReturn(false);

        ResponseEntity<GeneralResponse> response = clientController.updateClient(req, clientRequest, result);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testUpdateClient_NotFound() {
        ClientRequest clientRequest = new ClientRequest();
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        when(clientService.getFindById(1L)).thenReturn(null);

        ResponseEntity<GeneralResponse> response = clientController.updateClient(req, clientRequest, result);

        assertEquals(400, response.getStatusCodeValue());

    }

    @Test
    public void testUpdateClient_Exception() {
        ClientRequest clientRequest = new ClientRequest();
        when(clientService.getFindById(1L)).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = clientController.updateClient(req, clientRequest, result);

        assertEquals(400, response.getStatusCodeValue());
    }
}

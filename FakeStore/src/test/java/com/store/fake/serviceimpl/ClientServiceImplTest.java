package com.store.fake.serviceimpl;

import com.store.fake.domain.ClientDomain;
import com.store.fake.proxy.IUserServiceProxy;
import com.store.fake.proxy.response.users.DataResponseUsers;
import com.store.fake.repository.IClientRepository;
import com.store.fake.response.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClientServiceImplTest {

    @Mock
    private IUserServiceProxy userServiceProxy;

    @Mock
    private IClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetInformation_Failure() {
        DataResponseUsers dataResponseUsers = new DataResponseUsers();
        dataResponseUsers.setId(1);
        dataResponseUsers.setEmail("test@example.com");
        dataResponseUsers.setUsername("testuser");
        dataResponseUsers.setPassword("password");

        when(userServiceProxy.getInformation("test@example.com")).thenReturn(dataResponseUsers);

        ClientResponse response = clientService.getInformation("wronguser", "wrongpassword", "test@example.com");

        assertNull(response);
    }

    @Test
    void testGetLogin_Success() {
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        clientDomain.setEmail("test@example.com");
        clientDomain.setUsername("testuser");
        clientDomain.setPassword("password");
        clientDomain.setFirstname("First");
        clientDomain.setLastname("Last");

        when(clientRepository.findByUsername("testuser")).thenReturn(clientDomain);

        ClientResponse response = clientService.getLogin("testuser", "password", "test@example.com");

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("testuser", response.getUsername());
        assertEquals("First", response.getFirstname());
        assertEquals("Last", response.getLastname());
    }


    @Test
    void testGetAllClient() {
        ClientDomain client1 = new ClientDomain();
        client1.setIdClient(1L);
        client1.setEmail("test1@example.com");
        client1.setUsername("testuser1");
        client1.setFirstname("First1");
        client1.setLastname("Last1");

        ClientDomain client2 = new ClientDomain();
        client2.setIdClient(2L);
        client2.setEmail("test2@example.com");
        client2.setUsername("testuser2");
        client2.setFirstname("First2");
        client2.setLastname("Last2");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<ClientResponse> responses = clientService.getAllClient();

        assertEquals(2, responses.size());
        assertEquals(1L, responses.get(0).getId());
        assertEquals(2L, responses.get(1).getId());
    }

    @Test
    void testGetFindById() {
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        clientDomain.setEmail("test@example.com");
        clientDomain.setUsername("testuser");
        clientDomain.setPassword("password");
        clientDomain.setFirstname("First");
        clientDomain.setLastname("Last");

        when(clientRepository.findByIdClient(1L)).thenReturn(clientDomain);

        ClientResponse response = clientService.getFindById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("testuser", response.getUsername());
        assertEquals("password", response.getPassword());
        assertEquals("First", response.getFirstname());
        assertEquals("Last", response.getLastname());
    }

    @Test
    void testSaveClient() {
        ClientDomain clientDomain = new ClientDomain();
        clientDomain.setIdClient(1L);
        clientDomain.setEmail("test@example.com");
        clientDomain.setUsername("testuser");
        clientDomain.setPassword("password");
        clientDomain.setFirstname("First");
        clientDomain.setLastname("Last");

        when(clientRepository.saveAndFlush(clientDomain)).thenReturn(clientDomain);

        Long id = clientService.saveClient(clientDomain);

        assertEquals(1L, id);
    }
}

package com.store.fake.serviceimpl;

import com.store.fake.domain.ClientDomain;
import com.store.fake.proxy.IUserServiceProxy;
import com.store.fake.proxy.response.users.DataResponseUsers;
import com.store.fake.repository.IClientRepository;
import com.store.fake.response.ClientResponse;
import com.store.fake.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements IClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final IUserServiceProxy userServiceProxy;
    private final  IClientRepository clientRepository;

    public ClientServiceImpl(IUserServiceProxy userServiceProxy, IClientRepository clientRepository) {
        this.userServiceProxy = userServiceProxy;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientResponse getInformation(String username, String password, String email) {
        DataResponseUsers dataUser = userServiceProxy.getInformation(email);
        logger.info("Username {}", dataUser);

        if (dataUser.getUsername().equals(username.trim()) && dataUser.getPassword().equals(password.trim())) {
            return new ClientResponse(
                    Long.valueOf(dataUser.getId()),
                    dataUser.getEmail(),
                    dataUser.getUsername(),
                    null,
                    dataUser.getName().firstname(),
                    dataUser.getName().lastname()
            );
        }
        return null;
    }

    @Override
    public ClientResponse getLogin(String username, String password, String email) {
        ClientDomain dataUser = clientRepository.findByUsername(username.trim());

        if (dataUser.getUsername().equals(username.trim()) && dataUser.getPassword().equals(password.trim())) {
            return new ClientResponse(
                    dataUser.getIdClient(),
                    dataUser.getEmail(),
                    dataUser.getUsername(),
                    null,
                    dataUser.getFirstname(),
                    dataUser.getLastname()
            );
        }
        return null;
    }

    @Override
    public List<ClientResponse> getAllClient() {
        return clientRepository.findAll().stream()
                .map(client -> new ClientResponse(
                        client.getIdClient(),
                        client.getEmail(),
                        client.getUsername(),
                        null,
                        client.getFirstname(),
                        client.getLastname()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse getFindById(Long idClient) {
        ClientDomain client = clientRepository.findByIdClient(idClient);
        return new ClientResponse(
                client.getIdClient(),
                client.getEmail(),
                client.getUsername(),
                client.getPassword(),
                client.getFirstname(),
                client.getLastname()
        );
    }

    @Override
    public Long saveClient(ClientDomain clientDomain) {
        ClientDomain client = clientRepository.saveAndFlush(clientDomain);
        return client.getIdClient();
    }

}

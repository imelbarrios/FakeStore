package com.store.fake.serviceimpl;

import com.store.fake.proxy.IUserServiceProxy;
import com.store.fake.proxy.response.users.DataResponseUsers;
import com.store.fake.response.ClientResponse;
import com.store.fake.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final IUserServiceProxy userServiceProxy;


    @Override
    public ClientResponse getInformation(String username, String password, String email) {

        DataResponseUsers dataUser = userServiceProxy.getInformation(email);
        logger.info("Username {}",dataUser.toString());

        ClientResponse client= new ClientResponse();
        if(dataUser.getUsername().equals(username.trim()) && dataUser.getPassword().equals(password.trim())) {
            client.setId(dataUser.getId());
            client.setEmail(dataUser.getEmail());
            client.setUsername(dataUser.getUsername());
            client.setFirstname(dataUser.getName().firstname());
            client.setLastname(dataUser.getName().lastname());
        }



        return client;
    }

}

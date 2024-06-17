package com.store.fake.service;

import com.store.fake.domain.ClientDomain;
import com.store.fake.response.ClientResponse;

import java.util.List;

public interface IClientService {

    ClientResponse getInformation(String username, String password,String email);

    ClientResponse getLogin(String username, String password,String email);

    List<ClientResponse> getAllClient();

    ClientResponse getFindById(Long idClient);

    Long saveClient(ClientDomain clientDomain);


}

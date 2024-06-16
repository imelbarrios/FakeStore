package com.store.fake.service;

import com.store.fake.response.ClientResponse;

public interface IClientService {

    ClientResponse getInformation(String username, String password,String email);

}

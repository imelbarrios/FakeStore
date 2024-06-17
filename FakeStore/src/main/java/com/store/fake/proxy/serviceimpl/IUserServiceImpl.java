package com.store.fake.proxy.serviceimpl;

import com.store.fake.proxy.IUserServiceProxy;
import com.store.fake.proxy.response.users.DataResponseUsers;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class IUserServiceImpl implements IUserServiceProxy {
    private static final Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);
    private final RestTemplate restTemplate ;

    public IUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<DataResponseUsers> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        List<DataResponseUsers> responseBody = null;

        try {
            DataResponseUsers[] response = restTemplate.getForObject(
                     "https://fakestoreapi.com/users",
                    DataResponseUsers[].class);
            responseBody = Arrays.asList(response);
        } catch (Exception exception) {
            logger.error(
                    "Unexpected error while querying : {}",
                    exception);


        }

        return responseBody;
    }

    @Override
    public DataResponseUsers getInformation(String email) {
        List<DataResponseUsers> dataUser = getAll();

        logger.info("Email {}" , email);

        DataResponseUsers userResponse =    dataUser.stream()
                .filter(user -> user.getEmail().equals(email.trim()) )
                .findFirst()
                .orElse(new DataResponseUsers());
        logger.info("userResponse {}" , userResponse.toString());
        return userResponse;
    }
}

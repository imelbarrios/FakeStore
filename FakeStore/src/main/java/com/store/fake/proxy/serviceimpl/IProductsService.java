package com.store.fake.proxy.serviceimpl;

import com.store.fake.proxy.IProductsServiceProxy;
import com.store.fake.proxy.response.products.DataProductsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IProductsService  implements IProductsServiceProxy {
    private static final Logger logger = LoggerFactory.getLogger(IProductsServiceProxy.class);

    private final RestTemplate restTemplate ;

    public IProductsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DataProductsResponse> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        List<DataProductsResponse> responseBody = null;

        try {
            DataProductsResponse[] response = restTemplate.getForObject(
                    "https://fakestoreapi.com/products",
                    DataProductsResponse[].class);
            responseBody = Arrays.asList(response);
        } catch (Exception exception) {
            logger.error(
                    "Unexpected error while querying : {}",
                    exception);


        }

        return responseBody;
    }

    @Override
    public DataProductsResponse getIdProduct(int id) {

        List<DataProductsResponse> dataProducts = getAll();

        logger.info("Id {}", id);
        DataProductsResponse productsResponse = dataProducts.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(new DataProductsResponse());
        logger.info("productResponse {}", productsResponse.toString());

        return productsResponse;
    }

    @Override
    public List<DataProductsResponse> getCategory(String category) {
        List<DataProductsResponse> dataProducts = getAll();
        logger.info("Category {}", category);
        return    dataProducts.stream()
                .filter(product -> product.getCategory().equals(category.trim()) )
                 .collect(Collectors.toList());

    }
}

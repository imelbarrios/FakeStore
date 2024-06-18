package com.store.fake.proxy;

import com.store.fake.proxy.response.products.DataProductsResponse;
import com.store.fake.proxy.serviceimpl.IProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductsServiceImplTest {
    private RestTemplate restTemplate;
    private IProductsService iProductsService;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        iProductsService = new IProductsService(restTemplate);
    }

    @Test
    void testGetAllSuccess() {
        DataProductsResponse productsResponse = new DataProductsResponse();
        productsResponse.setId(1);
        productsResponse.setTitle("Product1");
        productsResponse.setCategory("Category1");

        DataProductsResponse[] mockResponse = {
                productsResponse,
                productsResponse
        };
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenReturn(mockResponse);

        List<DataProductsResponse> result = iProductsService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Product1", result.get(0).getTitle());
    }

    @Test
    void testGetAllException() {
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenThrow(new RuntimeException("API error"));

        List<DataProductsResponse> result = iProductsService.getAll();

        assertNull(result);
    }

    @Test
    void testGetIdProductFound() {
        DataProductsResponse productsResponse = new DataProductsResponse();
        productsResponse.setId(1);
        productsResponse.setTitle("Product1");
        productsResponse.setCategory("Category1");
        DataProductsResponse[] mockResponse = {
             productsResponse
        };
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenReturn(mockResponse);

        DataProductsResponse result = iProductsService.getIdProduct(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Product1", result.getTitle());
    }

    @Test
    void testGetIdProductNotFound() {
        DataProductsResponse productsResponse = new DataProductsResponse();
        productsResponse.setId(1);
        productsResponse.setTitle("Product1");
        productsResponse.setCategory("Category1");
        DataProductsResponse[] mockResponse = {
              productsResponse
        };
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenReturn(mockResponse);

        DataProductsResponse result = iProductsService.getIdProduct(3);

        assertNotNull(result);
        assertEquals(0, result.getId()); // Assuming default constructor sets id to 0
    }

    @Test
    void testGetCategoryFound() {
        DataProductsResponse productsResponse = new DataProductsResponse();
        productsResponse.setId(1);
        productsResponse.setTitle("Product1");
        productsResponse.setCategory("Category1");


        DataProductsResponse[] mockResponse = {
              productsResponse,productsResponse,productsResponse
        };
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenReturn(mockResponse);

        List<DataProductsResponse> result = iProductsService.getCategory("Category1");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Category1", result.get(0).getCategory());
    }

    @Test
    void testGetCategoryNotFound() {
        DataProductsResponse productsResponse = new DataProductsResponse();
        productsResponse.setId(1);
        productsResponse.setTitle("Product1");
        productsResponse.setCategory("Category1");
        DataProductsResponse[] mockResponse = {
          productsResponse,productsResponse
        };
        when(restTemplate.getForObject(anyString(), eq(DataProductsResponse[].class)))
                .thenReturn(mockResponse);

        List<DataProductsResponse> result = iProductsService.getCategory("Category3");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

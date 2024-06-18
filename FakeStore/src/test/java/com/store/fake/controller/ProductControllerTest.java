package com.store.fake.controller;

import com.store.fake.response.ProductResponse;
import com.store.fake.service.IProductService;
import com.store.fake.utils.GeneralResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private IProductService productService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts_Success() {
        List<ProductResponse> products = Arrays.asList(new ProductResponse(), new ProductResponse());
        when(productService.getAll()).thenReturn(products);

        ResponseEntity<GeneralResponse> response = productController.getAllProducts(request);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).getAll();
    }

    @Test
    public void testGetAllProducts_NotFound() {
        when(productService.getAll()).thenReturn(null);

        ResponseEntity<GeneralResponse> response = productController.getAllProducts(request);

        assertEquals(400, response.getStatusCodeValue());
        verify(productService, times(1)).getAll();
    }

    @Test
    public void testGetAllProducts_Exception() {
        when(productService.getAll()).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = productController.getAllProducts(request);

        assertEquals(500, response.getStatusCodeValue());
        verify(productService, times(1)).getAll();
    }

    @Test
    public void testGetCategoryProducts_Success() {
        List<ProductResponse> products = Arrays.asList(new ProductResponse(), new ProductResponse());
        when(productService.getCategory("electronics")).thenReturn(products);

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, "electronics");

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).getCategory("electronics");
    }

    @Test
    public void testGetCategoryProducts_NotFound() {
        when(productService.getCategory("electronics")).thenReturn(null);

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, "electronics");

        assertEquals(400, response.getStatusCodeValue());
        verify(productService, times(1)).getCategory("electronics");
    }

    @Test
    public void testGetCategoryProducts_Exception() {
        when(productService.getCategory("electronics")).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, "electronics");

        assertEquals(500, response.getStatusCodeValue());
        verify(productService, times(1)).getCategory("electronics");
    }

    @Test
    public void testGetIdProduct_Success() {
        ProductResponse product = new ProductResponse();
        when(productService.getIdProduct(1)).thenReturn(product);

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, 1);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).getIdProduct(1);
    }

    @Test
    public void testGetIdProduct_NotFound() {
        when(productService.getIdProduct(1)).thenReturn(null);

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, 1);

        assertEquals(400, response.getStatusCodeValue());
        verify(productService, times(1)).getIdProduct(1);
    }

    @Test
    public void testGetIdProduct_Exception() {
        when(productService.getIdProduct(1)).thenThrow(new RuntimeException("Exception"));

        ResponseEntity<GeneralResponse> response = productController.getCategoryProducts(request, 1);

        assertEquals(500, response.getStatusCodeValue());
        verify(productService, times(1)).getIdProduct(1);
    }
}

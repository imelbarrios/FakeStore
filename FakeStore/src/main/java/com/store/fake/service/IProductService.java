package com.store.fake.service;

import com.store.fake.response.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getAll();
    ProductResponse getIdProduct(int id);
    List<ProductResponse> getCategory(String category);
}

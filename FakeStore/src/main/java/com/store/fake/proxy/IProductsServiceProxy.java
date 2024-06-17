package com.store.fake.proxy;

import com.store.fake.proxy.response.products.DataProductsResponse;

import java.util.List;

public interface IProductsServiceProxy {
    List<DataProductsResponse> getAll();

    DataProductsResponse getIdProduct(int id);

    List<DataProductsResponse>getCategory(String category);
}

package com.store.fake.serviceimpl;

import com.store.fake.proxy.IProductsServiceProxy;
import com.store.fake.proxy.response.products.DataProductsResponse;
import com.store.fake.response.ProductResponse;
import com.store.fake.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final IProductsServiceProxy productsServiceProxy;

    public ProductServiceImpl(IProductsServiceProxy productsServiceProxy) {
        this.productsServiceProxy = productsServiceProxy;
    }


    @Override
    public List<ProductResponse> getAll() {
        List<DataProductsResponse> dataProducts = productsServiceProxy.getAll();

        return dataProducts.stream()
                .map(this::mapProduct)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getIdProduct(int id) {

        DataProductsResponse dataProductsResponse = productsServiceProxy.getIdProduct(id);

        return mapProduct(dataProductsResponse);
    }

    @Override
    public List<ProductResponse> getCategory(String category) {
        List<DataProductsResponse> dataProducts = productsServiceProxy.getCategory(category);

        return dataProducts.stream()
                .map(this::mapProduct)
                .collect(Collectors.toList());
    }

    private ProductResponse mapProduct(DataProductsResponse response){
        logger.info("In map Product {}", response);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(response.getId());
        productResponse.setTitle(response.getTitle());
        productResponse.setPrice(response.getPrice());
        productResponse.setCategory(response.getCategory());
        productResponse.setImage(response.getImage());
        logger.info("Out map Product {}", productResponse.toString());
        return productResponse;
    }
}

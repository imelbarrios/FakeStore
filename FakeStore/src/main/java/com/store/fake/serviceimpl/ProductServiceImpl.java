package com.store.fake.serviceimpl;

import com.store.fake.proxy.IProductsServiceProxy;
import com.store.fake.proxy.response.products.DataProductsResponse;
import com.store.fake.response.ProductResponse;
import com.store.fake.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<ProductResponse> productResponses = new ArrayList<>();

        for(DataProductsResponse data : dataProducts){
            productResponses.add(mapProduct(data));
        }

       return productResponses;
    }

    @Override
    public ProductResponse getIdProduct(int id) {

        DataProductsResponse dataProductsResponse = productsServiceProxy.getIdProduct(id);

        return mapProduct(dataProductsResponse);
    }

    @Override
    public List<ProductResponse> getCategory(String category) {
        List<DataProductsResponse> dataProducts = productsServiceProxy.getCategory(category);
        List<ProductResponse> productResponses = new ArrayList<>();

        for(DataProductsResponse data : dataProducts){
            productResponses.add(mapProduct(data));
        }

        return productResponses;
    }

    private ProductResponse mapProduct(DataProductsResponse response){
        ProductResponse productResponse = new ProductResponse(response.getId(),response.getTitle(),response.getPrice(),
                response.getCategory(), response.getImage() );

        return productResponse;
    }
}

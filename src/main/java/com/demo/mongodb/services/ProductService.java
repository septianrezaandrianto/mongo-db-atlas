package com.demo.mongodb.services;

import com.demo.mongodb.dtos.GeneralPageResponse;
import com.demo.mongodb.dtos.GeneralResponse;
import com.demo.mongodb.dtos.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    GeneralResponse<Object> create(ProductRequest productRequest);
    GeneralPageResponse<Object> getAll();
    GeneralPageResponse<Object> getPage(String name, Integer pageNumber, Integer pageSize);
    GeneralResponse<Object> getById(String id);
    GeneralResponse<Object> update(String id, ProductRequest productRequest);
    GeneralResponse<Object> delete(String id);

}

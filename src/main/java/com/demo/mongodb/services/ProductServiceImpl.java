package com.demo.mongodb.services;

import com.demo.mongodb.constants.MongodbConstant;
import com.demo.mongodb.dtos.GeneralPageResponse;
import com.demo.mongodb.dtos.GeneralResponse;
import com.demo.mongodb.dtos.ProductRequest;
import com.demo.mongodb.entities.Product;
import com.demo.mongodb.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public GeneralResponse<Object> create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .build();

        Product savedProduct = productRepository.save(product);
        return GeneralResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_CREATE_RESPONSE_MESSAGE)
                .data(savedProduct)
                .build();
    }

    @Override
    public GeneralPageResponse<Object> getAll() {
        List<Product> productList = productRepository.findAll();

        return GeneralPageResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_RESPONSE_MESSAGE)
                .dataList(productList)
                .totalData(productList.size())
                .build();
    }

    @Override
    public GeneralPageResponse<Object> getPage(String name, Integer pageNumber, Integer pageSize) {
        Pageable pageable =  PageRequest.of(pageNumber, pageSize);
        Page<Product> getDataPage = productRepository.getDatapage(name, pageable);

        return GeneralPageResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_RESPONSE_MESSAGE)
                .totalData(getDataPage.getTotalElements())
                .dataList(getDataPage.getContent())
                .build();
    }

    @Override
    public GeneralResponse<Object> getById(String id) {
        Product product = productRepository.getByIdProduct(id);

        return GeneralResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_RESPONSE_MESSAGE)
                .data(product)
                .build();
    }

    @Override
    public GeneralResponse<Object> update(String id, ProductRequest productRequest) {
        Product product = productRepository.getByIdProduct(id);

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setQuantity(productRequest.getQuantity());
        product.setPrice(productRequest.getPrice());

        return GeneralResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_UPDATE_RESPONSE_MESSAGE)
                .data(productRepository.save(product))
                .build();
    }

    @Override
    public GeneralResponse<Object> delete(String id) {
        Product product = productRepository.getByIdProduct(id);
        productRepository.delete(product);
        return GeneralResponse.builder()
                .responseCode(MongodbConstant.Response.SUCCESS_RESPONSE_CODE)
                .responseMessage(MongodbConstant.Response.SUCCESS_DELETE_RESPONSE_MESSAGE)
                .build();
    }

}

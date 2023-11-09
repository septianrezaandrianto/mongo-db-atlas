package com.demo.mongodb.repositories;

import com.demo.mongodb.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{name:{$regex: ?0} }")
    Page<Product> getDatapage(String name, Pageable pageable);

    @Query("{id: ?0}")
    Product getByIdProduct(String id);

}

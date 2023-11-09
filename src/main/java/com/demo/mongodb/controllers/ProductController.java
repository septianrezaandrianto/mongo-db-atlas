package com.demo.mongodb.controllers;

import com.demo.mongodb.dtos.GeneralPageResponse;
import com.demo.mongodb.dtos.GeneralResponse;
import com.demo.mongodb.dtos.ProductRequest;
import com.demo.mongodb.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create")
    public ResponseEntity<GeneralResponse<Object>> create(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.create(productRequest));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<GeneralPageResponse<Object>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping(value = "/getPage")
    public ResponseEntity<GeneralPageResponse<Object>> getPage(@RequestParam(value="name", defaultValue= "")String name,
                                                               @RequestParam(value="pageNumber")Integer pageNumber,
                                                               @RequestParam(value="pageSize")Integer pageSize) {
        return ResponseEntity.ok(productService.getPage(name, pageNumber, pageSize));
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<GeneralResponse<Object>> getById(@RequestParam(value="id")String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<GeneralResponse<Object>> update(@RequestParam(value="id")String id,
                                                          @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<GeneralResponse<Object>> delete(@RequestParam(value="id")String id) {
        return ResponseEntity.ok(productService.delete(id));
    }

}

package com.technospark.api.restapi.service;

import com.technospark.api.restapi.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product createProduct(Product product, long categoryId);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}

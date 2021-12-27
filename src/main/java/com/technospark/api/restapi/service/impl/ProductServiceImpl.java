package com.technospark.api.restapi.service.impl;

import com.technospark.api.restapi.entity.Category;
import com.technospark.api.restapi.entity.Product;
import com.technospark.api.restapi.repository.CategoryRepository;
import com.technospark.api.restapi.repository.ProductRepository;
import com.technospark.api.restapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product, long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> result = productRepository.findById(product.getId());
        if(result.isPresent()){
           return productRepository.save(product);
        } else {
            log.error("Record not found" + product.getId());
            return null;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

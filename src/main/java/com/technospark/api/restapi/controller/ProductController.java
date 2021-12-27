package com.technospark.api.restapi.controller;

import com.technospark.api.restapi.entity.Product;
import com.technospark.api.restapi.model.ProductRequestDTO;
import com.technospark.api.restapi.model.ProductUpdateRequestDTO;
import com.technospark.api.restapi.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductService productService;
    private ModelMapper mapper = new ModelMapper();
    @GetMapping("/")
    @ApiImplicitParam(name = "Token", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/")
    @ApiImplicitParam(name = "Token", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public Product insertProduct(ProductRequestDTO request){
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return productService.createProduct(product,request.getCategoryId());
    }

    @PutMapping("/")
    @ApiImplicitParam(name = "Token", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public Product updateProduct(ProductUpdateRequestDTO request){
        Product product = mapper.map(request, Product.class);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ApiImplicitParam(name = "Token", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}

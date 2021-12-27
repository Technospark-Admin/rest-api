package com.technospark.api.restapi.controller;

import com.technospark.api.restapi.entity.Category;
import com.technospark.api.restapi.entity.Product;
import com.technospark.api.restapi.model.CategoryRequestDTO;
import com.technospark.api.restapi.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/category")
public class CategoryController {

    private ModelMapper mapper = new ModelMapper();
    @Autowired
    private CategoryService categoryService;
    @ApiImplicitParam(name = "", value = "Access Token",
            required = true, allowEmptyValue = false,
            paramType = "header", example = "Bearer access_token")
    @GetMapping("/")
    public List<Category> categoryList () {
        return categoryService.getAllCategory();
    }
    @ApiImplicitParam(name = "Token", value = "Access Token",
            required = true, allowEmptyValue = false, paramType = "header",
            example = "Bearer access_token")
    @PostMapping("/")
    public Category createCategory(CategoryRequestDTO request){
        Category category = mapper.map(request, Category.class);
        return categoryService.createCategory(category);
    }

}

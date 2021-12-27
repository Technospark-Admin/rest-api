package com.technospark.api.restapi.service;

import com.technospark.api.restapi.entity.Category;
import com.technospark.api.restapi.model.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {
    
    List<Category> getAllCategory() ;


    Category createCategory(Category category);
}

package com.technospark.api.restapi.service.impl;

import com.technospark.api.restapi.entity.Category;
import com.technospark.api.restapi.repository.CategoryRepository;
import com.technospark.api.restapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository ;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll() ;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}

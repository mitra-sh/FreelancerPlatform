package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public String showAllCategories(){
       return categoryRepository.findAllDistinctCategories().toString();
    }
}

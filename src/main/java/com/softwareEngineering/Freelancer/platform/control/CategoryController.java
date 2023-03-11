package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/showAllCategories")
    public String showAllCategories() {
        String categories=categoryService.showAllCategories();
        return categories;
    }
}

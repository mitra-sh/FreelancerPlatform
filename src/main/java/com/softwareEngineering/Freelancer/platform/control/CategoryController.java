package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.repository.CategoryRepository;
import com.softwareEngineering.Freelancer.platform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/showAllCategories")
    public String showAllCategories() {
        return categoryService.showAllCategories();
    }
}

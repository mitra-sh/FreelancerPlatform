package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import com.softwareEngineering.Freelancer.platform.service.AuditLogService;
import com.softwareEngineering.Freelancer.platform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuditLogService auditLogService;

    @RequestMapping("/showAllCategories")
    public ResponseEntity showAllCategories(@RequestBody UsernameRequest request) {
        String categories=categoryService.showAllCategories();
        auditLogService.log(request.getUsername(),"request to view all categories","");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categories);
    }
}

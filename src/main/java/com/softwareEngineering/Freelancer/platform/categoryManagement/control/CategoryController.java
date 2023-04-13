package com.softwareEngineering.Freelancer.platform.categoryManagement.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryController extends BaseController {
    @RequestMapping("/showAllCategories")

    public ResponseEntity showAllCategories(@RequestBody UsernameRequest request) {
        String categories = baseService.showAllCategories();
        baseService.log(request.getUsername(), "request to view all categories", "");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categories);
    }
}

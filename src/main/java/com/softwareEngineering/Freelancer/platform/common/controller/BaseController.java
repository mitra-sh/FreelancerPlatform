

package com.softwareEngineering.Freelancer.platform.common.controller;


import com.softwareEngineering.Freelancer.platform.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BaseController {
    @Autowired
    public BaseService baseService;
}

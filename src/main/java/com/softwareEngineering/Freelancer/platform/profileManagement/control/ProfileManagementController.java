
package com.softwareEngineering.Freelancer.platform.profileManagement.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderSkillUpdateRequest;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProfileManagementController extends BaseController {
    @RequestMapping("/showAllServiceProviders")
    public ResponseEntity showAllServiceProviders(@RequestBody UsernameRequest request) {
        baseService.log(request.getUsername(), "request to view all service providers", "");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(baseService.showAllServiceProviders());
    }

    @RequestMapping("/updateServiceProviderSkills")
    public ResponseEntity updateServiceProviderSkills(@RequestBody ServiceProviderSkillUpdateRequest request) {
        ServiceProvider serviceProvider = baseService.updateServiceProviderSkills(request);
        baseService.log(request.getUsername(), "request to update his/her profile", "a new info: " + request.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
    }
}

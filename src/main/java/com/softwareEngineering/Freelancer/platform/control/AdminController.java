package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import com.softwareEngineering.Freelancer.platform.service.EndUserService;
import com.softwareEngineering.Freelancer.platform.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private EndUserService endUserService;

    @RequestMapping("/admin/deleteAccount")
    public ResponseEntity deleteAccount(@RequestBody UsernameRequest request) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = endUserService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            serviceProviderService.deleteServiceProvider(serviceProvider);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("the service provider was deleted");
        } else if (endUser != null) {
            endUserService.deleteEndUser(endUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("the client  was deleted");
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this username");
    }
}

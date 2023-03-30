package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.CreateAccountRequest;
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
public class UserController {
    @Autowired
    private EndUserService endUserService;
    @Autowired
    private ServiceProviderService serviceProviderService;

    @RequestMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody CreateAccountRequest request) {
        if (request.getType().equals("client")) {
            endUserService.createNewEndUser(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The client successfully registered to the system");
        } else {
            serviceProviderService.createNewServiceProvider(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The service provider successfully registered to the system");
        }
    }

    @RequestMapping("/findAUser")
    public ResponseEntity findAUserByEmail(@RequestBody String email) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByEmail(email);
        EndUser endUser = endUserService.findEndUserByEmail(email);
        if (serviceProvider != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider.toString());
        } else if (endUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser.toString());
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }

    @RequestMapping("/findUserByUsername")
    public ResponseEntity findUserByUsername(@RequestBody String username) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByUsername(username);
        EndUser endUser = endUserService.findEndUserByUsername(username);
        if (serviceProvider != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider.toString());
        } else if (endUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser.toString());
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }


}

package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.*;
import com.softwareEngineering.Freelancer.platform.service.AuditLogService;
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
    @Autowired
    private AuditLogService auditLogService;


    @RequestMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody CreateAccountRequest request) {
        if (request.getType().equals("client") ) {
            endUserService.createNewEndUser(request);
            auditLogService.log(request.getUsername(),"registered to the system","the role: "+request.getType());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        } else if (request.getType().equals("service provider")){
            serviceProviderService.createNewServiceProvider(request);
            auditLogService.log(request.getUsername(),"registered to the system","the role: "+request.getType());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        }
        else if (request.getType().equals("admin")) {
            serviceProviderService.createNewServiceProvider(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("error, there is no such user");
        }
    }

    @RequestMapping("/findAUser")
    public ResponseEntity findAUserByEmail(@RequestBody EmailRequest request) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByEmail(request.getEmail());
        EndUser endUser = endUserService.findEndUserByEmail(request.getEmail());
        if (serviceProvider != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if (endUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }

    @RequestMapping("/findUserByUsername")
    public ResponseEntity findUserByUsername(@RequestBody SearchForUserRequest request) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = endUserService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            auditLogService.log(request.getMyUsername(),"search for a user","the filter: "+request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if (endUser != null) {
            auditLogService.log(request.getMyUsername(),"search for a user","the filter: "+request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }

    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        ServiceProvider serviceProvider = serviceProviderService.findServiceProviderByEmailAndPassword
                (request.getEmail(), request.getPassword());
        EndUser endUser = endUserService.findEndUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if (serviceProvider != null) {
            auditLogService.log(serviceProvider.getUsername(),"logged in to the system"," ");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if(endUser!=null) {
            auditLogService.log(endUser.getUsername(),"logged in to the system"," ");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this username and password");
    }
}

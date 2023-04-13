
package com.softwareEngineering.Freelancer.platform.registration.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.CreateAccountRequest;
import com.softwareEngineering.Freelancer.platform.request.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RegistrationController extends BaseController {
    @RequestMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody CreateAccountRequest request) {
        if (request.getType().equals("client")) {
            baseService.createNewEndUser(request);
            baseService.log(request.getUsername(), "registered to the system", "the role: " + request.getType());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        } else if (request.getType().equals("service provider")) {
            baseService.createNewServiceProvider(request);
            baseService.log(request.getUsername(), "registered to the system", "the role: " + request.getType());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        } else if (request.getType().equals("admin")) {
            baseService.createNewServiceProvider(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(request);
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("error, there is no such user");
        }
    }

    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByEmailAndPassword
                (request.getEmail(), request.getPassword());
        EndUser endUser = baseService.findEndUserByEmailAndPassword(request.getEmail(), request.getPassword());
        if (serviceProvider != null) {
            baseService.log(serviceProvider.getUsername(), "logged in to the system", " ");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if (endUser != null) {
            baseService.log(endUser.getUsername(), "logged in to the system", " ");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this username and password");
    }
}

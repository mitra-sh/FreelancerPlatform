package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import com.softwareEngineering.Freelancer.platform.request.SignUpRequest;
import com.softwareEngineering.Freelancer.platform.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EndUserController {
    @Autowired
    private EndUserService endUserService;
    @RequestMapping("/createNewServiceRequestForEndUser")
    public ResponseEntity createNewServiceRequest(@RequestBody  RequestForCreatingNewServiceRequest request){
        endUserService.createNewServiceRequestForEndUser(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Service request successfully added to the system." );
    }
    @RequestMapping("/signupAsEndUser")
    public ResponseEntity signupAsEndUser(@RequestBody SignUpRequest request){
       endUserService.signUpAsEndUser(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Client successfully signed up to the system." );
    }



}

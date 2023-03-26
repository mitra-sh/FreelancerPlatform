package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import com.softwareEngineering.Freelancer.platform.request.SignUpRequest;
import com.softwareEngineering.Freelancer.platform.service.EndUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndUserController {
    @Autowired
    private EndUserService endUserService;
    @RequestMapping("/createNewServiceRequestForEndUser")
    public void createNewServiceRequest(@RequestBody  RequestForCreatingNewServiceRequest request){
        endUserService.createNewServiceRequestForEndUser(request);
    }
    @RequestMapping("/signupAsEndUser")
    public void signupAsEndUser(@RequestBody SignUpRequest request){
       endUserService.signUpAsEndUser(request);
    }



}

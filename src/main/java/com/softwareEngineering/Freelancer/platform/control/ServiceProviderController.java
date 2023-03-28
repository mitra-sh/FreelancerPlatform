package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.CreateServiceProviderProfileRequest;
import com.softwareEngineering.Freelancer.platform.request.MostMatchedServiceProviderRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderSkillUpdateRequest;
import com.softwareEngineering.Freelancer.platform.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @RequestMapping("/createServiceProviderProfile")
    public ResponseEntity createServiceProviderProfile(@RequestBody CreateServiceProviderProfileRequest request) {
        serviceProviderService.addServiceProvider(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The service provider profile successfully created" );
    }

    @RequestMapping("/showAllServiceProviders")
    public ResponseEntity showAllServiceProviders() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProviderService.showAllServiceProviders().toString());

    }

    @RequestMapping("/updateServiceProviderSkills")
    public ResponseEntity updateServiceProviderSkills(@RequestBody ServiceProviderSkillUpdateRequest request) {
        serviceProviderService.updateServiceProviderSkills(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The service provider profile was successfully updated" );
    }

    @RequestMapping("/rateAServiceProvider")
    public ResponseEntity rateAServiceProvider(@RequestBody ServiceProviderRatingRequest request) {
        serviceProviderService.rateAServiceProvider(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The rating for the service provider was successfully submitted" );
    }

    @RequestMapping("/findTheMostMatchedServiceProvider")
    public ResponseEntity findTheMostMatchedServiceProvider(@RequestBody MostMatchedServiceProviderRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body("The most matched service provider is : "+"\n"
                        +serviceProviderService.findTheMostMatchedServiceProviderForAUser(request.getUsername()));
    }
}

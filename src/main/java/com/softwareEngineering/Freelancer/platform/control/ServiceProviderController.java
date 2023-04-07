package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import com.softwareEngineering.Freelancer.platform.request.*;
import com.softwareEngineering.Freelancer.platform.service.ServiceProviderService;
import com.softwareEngineering.Freelancer.platform.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private ServiceRequestService serviceRequestService;


    @RequestMapping("/createServiceProviderProfile")
    public ResponseEntity createServiceProviderProfile(@RequestBody CreateServiceProviderProfileRequest request) {
        serviceProviderService.addServiceProvider(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The service provider profile successfully created" );
    }

    @RequestMapping("/showAllServiceProviders")
    public ResponseEntity showAllServiceProviders() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProviderService.showAllServiceProviders());
    }

    @RequestMapping("/updateServiceProviderSkills")
    public ResponseEntity updateServiceProviderSkills(@RequestBody ServiceProviderSkillUpdateRequest request) {
        ServiceProvider serviceProvider=serviceProviderService.updateServiceProviderSkills(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider );
    }

    @RequestMapping("/rateAServiceProvider")
    public ResponseEntity rateAServiceProvider(@RequestBody ServiceProviderRatingRequest request) {
        serviceProviderService.rateAServiceProvider(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The rating for the service provider was successfully submitted" );
    }

    @RequestMapping("/findTheMostMatchedServiceProvider")
    public ResponseEntity findTheMostMatchedServiceProvider(@RequestBody UsernameRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body("The most matched service provider is : "+"\n"
                        +serviceProviderService.findTheMostMatchedServiceProviderForAUser(request.getUsername()));
    }
    @RequestMapping("/viewAllTickets")
    public ResponseEntity viewAllTickets() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceRequestService.viewAllTickets());
    }
    @RequestMapping("/acceptTicket")
    public ResponseEntity acceptTicket(AcceptTicketRequest request) {
       ServiceProvider updatedServiceProvider= serviceProviderService.acceptTicket(request.getUsername(),
               request.getTicketNumber());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(updatedServiceProvider);
    }
    @RequestMapping("/viewServiceProviderTickets")
    public ResponseEntity viewServiceProviderTickets(UsernameRequest request) {
        ServiceProvider serviceProvider=serviceProviderService.findServiceProviderByUsername(request.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProvider.getServiceRequests());
    }
}

package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.model.Skill;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import com.softwareEngineering.Freelancer.platform.request.*;
import com.softwareEngineering.Freelancer.platform.service.AuditLogService;
import com.softwareEngineering.Freelancer.platform.service.ServiceProviderService;
import com.softwareEngineering.Freelancer.platform.service.ServiceRequestService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private ServiceRequestService serviceRequestService;
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private AuditLogService auditLogService;


    @RequestMapping("/showAllServiceProviders")
    public ResponseEntity showAllServiceProviders(@RequestBody UsernameRequest request) {
        auditLogService.log(request.getUsername(),"request to view all service providers","");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProviderService.showAllServiceProviders());
    }

    @RequestMapping("/updateServiceProviderSkills")
    public ResponseEntity updateServiceProviderSkills(@RequestBody ServiceProviderSkillUpdateRequest request) {
        ServiceProvider serviceProvider=serviceProviderService.updateServiceProviderSkills(request);
        auditLogService.log(request.getUsername(),"request to update his/her profile","a new info: "+request.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider );
    }

    @RequestMapping("/rateAServiceProvider")
    public ResponseEntity rateAServiceProvider(@RequestBody ServiceProviderRatingRequest request) {
        serviceProviderService.rateAServiceProvider(request);
        auditLogService.log(request.getUsername(),"rate a service provider","username of service provider: "+request.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The rating for the service provider was successfully submitted" );
    }

    @RequestMapping("/findTheMostMatchedServiceProvider")
    public ResponseEntity findTheMostMatchedServiceProvider(@RequestBody UsernameRequest request) {
        auditLogService.log("system","finding the most matched service provider","username of service provider: "+request.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body("The most matched service provider is : "+"\n"
                        +serviceProviderService.findTheMostMatchedServiceProviderForAUser(request.getUsername()));
    }
    @RequestMapping("/viewAllTickets")
    public ResponseEntity viewAllTickets(@RequestBody UsernameRequest request) {
        auditLogService.log(request.getUsername(),"request to view all tickets in the system","");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceRequestService.viewAllTickets());
    }
    @Transactional
    @RequestMapping("/viewAllNonTakenTickets")
    public ResponseEntity viewAllNonTakenTickets(@RequestBody UsernameRequest request) {
        List<ServiceRequest> listOfTickets=serviceRequestService.viewAllTickets();
        List<ServiceRequest> listOfNonTakenTickets=new ArrayList<ServiceRequest>();
        for (ServiceRequest ticket:listOfTickets){
            if(ticket.getStatus().equals("not_taken")){
                listOfNonTakenTickets.add(ticket);
            }
        }
        auditLogService.log(request.getUsername(),"request to view all available tickets in the system","");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(listOfNonTakenTickets);
    }
    @Transactional
    @RequestMapping("/acceptTicket")
    public ResponseEntity acceptTicket(@RequestBody AcceptTicketRequest request) {
       ServiceProvider updatedServiceProvider= serviceProviderService.acceptTicket(request.getUsername(),
               request.getId());
        auditLogService.log(request.getUsername(),"accept a ticket","the id of ticket: "+request.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(updatedServiceProvider);
    }

    @Transactional
    @RequestMapping("/completeATicket")
    public ResponseEntity completeATicket(@RequestBody AcceptTicketRequest request) {
        ServiceProvider serviceProvider=serviceProviderService.findServiceProviderByUsername(request.getUsername());
        Optional<ServiceRequest> optionalServiceRequest= serviceRequestRepository.findById(request.getId());
        ServiceRequest serviceRequest=new ServiceRequest();
        if (optionalServiceRequest.isPresent()) {
            serviceRequest= optionalServiceRequest.get();
        } else {
            throw new RuntimeException("ServiceRequest not found ");
        }
        if(serviceProvider.getServiceRequests().contains(serviceRequest)){
            int index=serviceProvider.getServiceRequests().indexOf(serviceRequest);
            serviceProvider.getServiceRequests().get(index).setStatus("completed");
        }
        auditLogService.log(request.getUsername(),"complete a ticket","the id of ticket: "+request.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceRequest);
    }



    @RequestMapping("/viewServiceProviderTickets")
    public ResponseEntity viewServiceProviderTickets(@RequestBody ViewServiceProviderTicketsRequest request) {
        ServiceProvider serviceProvider=serviceProviderService.findServiceProviderByUsername(request.getUsernameOfServiceProvider());
        auditLogService.log(request.getMyUsername(),"request to view all tickets accepted by a particular service provider"
                ,"the username of service provider: "+request.getUsernameOfServiceProvider());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProvider.getServiceRequests());
    }
    @RequestMapping("/searchTicketsBySkills")
    public ResponseEntity searchTicketsBySkills(@RequestBody SearchTicketRequest request) {
       List<ServiceRequest> tickets=new ArrayList<ServiceRequest>();
        List<ServiceRequest> serviceRequests=serviceRequestRepository.findAll();
       for (String skill:request.getSkills()){
           for(ServiceRequest serviceRequest:serviceRequests){
               if (serviceRequest.getRequiredSkills().contains(skill)){
                   tickets.add(serviceRequest);
               }
           }
       }
        auditLogService.log(request.getUsername(),"search a ticket"
                ,"the filters: "+request.getSkills());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(tickets);

    }
}


package com.softwareEngineering.Freelancer.platform.search.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class SearchController extends BaseController {
    @RequestMapping("/viewEndUserTickets")
    public ResponseEntity viewEndUserTickets(@RequestBody ViewEndUserTicketsRequest request){
        EndUser endUser=baseService.findEndUserByUsername(request.getUsernameOfEndUser());
        if(endUser.getServiceRequests()!=null) {
            baseService.log(request.getMyUsername(),"request to view all tickets added to the system by an end user","username of end user : "+request.getUsernameOfEndUser());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser.getServiceRequests());
        }
        else throw new RuntimeException("there is no ticket requested by this user ");
    }
    @Transactional
    @RequestMapping("/viewAllNonTakenTickets")
    public ResponseEntity viewAllNonTakenTickets(@RequestBody UsernameRequest request) {
        List<ServiceRequest> listOfTickets=baseService.viewAllTickets();
        List<ServiceRequest> listOfNonTakenTickets=new ArrayList<ServiceRequest>();
        for (ServiceRequest ticket:listOfTickets){
            if(ticket.getStatus().equals("not_taken")){
                listOfNonTakenTickets.add(ticket);
            }
        }
        baseService.log(request.getUsername(),"request to view all available tickets in the system","");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(listOfNonTakenTickets);
    }
    @RequestMapping("/viewServiceProviderTickets")
    public ResponseEntity viewServiceProviderTickets(@RequestBody ViewServiceProviderTicketsRequest request) {
        ServiceProvider serviceProvider=baseService.findServiceProviderByUsername(request.getUsernameOfServiceProvider());
        baseService.log(request.getMyUsername(),"request to view all tickets accepted by a particular service provider"
                ,"the username of service provider: "+request.getUsernameOfServiceProvider());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceProvider.getServiceRequests());
    }
    @RequestMapping("/searchTicketsBySkills")
    public ResponseEntity searchTicketsBySkills(@RequestBody SearchTicketRequest request) {
        List<ServiceRequest> tickets=new ArrayList<ServiceRequest>();
        List<ServiceRequest> serviceRequests=baseService.serviceRequestRepository.findAll();
        for (String skill:request.getSkills()){
            for(ServiceRequest serviceRequest:serviceRequests){
                if (serviceRequest.getRequiredSkills().contains(skill)){
                    tickets.add(serviceRequest);
                }
            }
        }
        baseService.log(request.getUsername(),"search a ticket"
                ,"the filters: "+request.getSkills());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(tickets);

    }
    @RequestMapping("/searchServiceProvidersBySkills")
    public ResponseEntity searchServiceProvidersBySkills(@RequestBody SearchTicketRequest request) {
        List<ServiceProvider> serviceProviders=baseService.showAllServiceProviders();
        List<ServiceProvider> finalServiceProviders=new ArrayList<ServiceProvider>();
        for (String skill:request.getSkills()){
            for(ServiceProvider serviceProvider:serviceProviders){
                if (serviceProvider.getSkills().contains(skill)){
                    finalServiceProviders.add(serviceProvider);
                }
            }
        }
        baseService.log(request.getUsername(),"search a service provider by list of skills"
                ,"list of requested skills: "+request.getSkills());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(finalServiceProviders);

    }
    @RequestMapping("/findAUser")
    public ResponseEntity findAUserByEmail(@RequestBody EmailRequest request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByEmail(request.getEmail());
        EndUser endUser = baseService.findEndUserByEmail(request.getEmail());
        if (serviceProvider != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if (endUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }

    @RequestMapping("/findUserByUsername")
    public ResponseEntity findUserByUsername(@RequestBody SearchForUserRequest request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = baseService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            baseService.log(request.getMyUsername(),"search for a user","the filter: "+request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(serviceProvider);
        } else if (endUser != null) {
            baseService.log(request.getMyUsername(),"search for a user","the filter: "+request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }


}

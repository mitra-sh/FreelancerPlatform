package com.softwareEngineering.Freelancer.platform.ticketmanagement;

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

import java.util.Optional;

@RestController
@CrossOrigin
public class TicketManagementController extends BaseController {

    @RequestMapping("/createNewServiceRequestForEndUser")
    public ResponseEntity createNewServiceRequest(@RequestBody RequestForCreatingNewServiceRequest request) {
        EndUser endUser = baseService.createNewServiceRequestForEndUser(request);
        baseService.log(request.getUsername(), "request a new ticket", request.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(endUser);
    }


    @RequestMapping("/viewAllTickets")
    public ResponseEntity viewAllTickets(@RequestBody UsernameRequest request) {
        baseService.log(request.getUsername(), "request to view all tickets in the system", "");
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(baseService.viewAllTickets());
    }

    @Transactional
    @RequestMapping("/acceptTicket")
    public ResponseEntity acceptTicket(@RequestBody AcceptTicketRequest request) {
        ServiceProvider updatedServiceProvider = baseService.acceptTicket(request.getUsername(),
                request.getId());
        baseService.log(request.getUsername(), "accept a ticket", "the id of ticket: " + request.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(updatedServiceProvider);
    }

    @Transactional
    @RequestMapping("/completeATicket")
    public ResponseEntity completeATicket(@RequestBody AcceptTicketRequest request) {
        ServiceRequest serviceRequest = baseService.updateServiceRequestRepository(request.getId());
        baseService.log(request.getUsername(), "complete a ticket", "the id of ticket: " + request.getId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(serviceRequest);
    }


}

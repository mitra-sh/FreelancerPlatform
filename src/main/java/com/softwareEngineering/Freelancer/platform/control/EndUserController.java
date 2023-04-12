package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import com.softwareEngineering.Freelancer.platform.request.ViewEndUserTicketsRequest;
import com.softwareEngineering.Freelancer.platform.service.AuditLogService;
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
    @Autowired
    private AuditLogService auditLogService;

    @RequestMapping("/createNewServiceRequestForEndUser")
    public ResponseEntity createNewServiceRequest(@RequestBody  RequestForCreatingNewServiceRequest request){
        EndUser endUser=endUserService.createNewServiceRequestForEndUser(request);
        auditLogService.log(request.getUsername(),"request a new ticket",request.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).
                body(endUser);
    }

    @RequestMapping("/viewEndUserTickets")
    public ResponseEntity viewEndUserTickets(@RequestBody ViewEndUserTicketsRequest request){
        EndUser endUser=endUserService.findEndUserByUsername(request.getUsernameOfEndUser());
        if(endUser.getServiceRequests()!=null) {
            auditLogService.log(request.getMyUsername(),"request to view all tickets added to the system by an end user","username of end user : "+request.getUsernameOfEndUser());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(endUser.getServiceRequests());
        }
        else throw new RuntimeException("there is no ticket requested by this user ");
    }



}

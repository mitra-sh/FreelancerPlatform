package com.softwareEngineering.Freelancer.platform.administation.control;

import com.softwareEngineering.Freelancer.platform.common.controller.BaseController;
import com.softwareEngineering.Freelancer.platform.model.AuditLog;
import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.request.ReportRequestBasedOnTime;
import com.softwareEngineering.Freelancer.platform.request.UsernameRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AdministrationController extends BaseController {

    @RequestMapping("/admin/deleteAccount")
    public ResponseEntity deleteAccount(@RequestBody UsernameRequest request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = baseService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            baseService.deleteServiceProvider(serviceProvider);
            baseService.log("admin","delete an account",request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("the service provider was deleted");
        } else if (endUser != null) {
            baseService.deleteEndUser(endUser);
            baseService.log("admin","delete an account",request.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("the client  was deleted");
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this username");
    }
     @RequestMapping("/admin/report")
    public ResponseEntity report(@RequestBody UsernameRequest request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = baseService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(baseService.auditLogRepository.findByUsername(serviceProvider.getUsername()));
        } else if (endUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(baseService.auditLogRepository.findByUsername(endUser.getUsername()));
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }
    @RequestMapping("/admin/reportFilteredByTime")
    public ResponseEntity reportFilteredByTime(@RequestBody ReportRequestBasedOnTime request) {
        ServiceProvider serviceProvider = baseService.findServiceProviderByUsername(request.getUsername());
        EndUser endUser = baseService.findEndUserByUsername(request.getUsername());
        if (serviceProvider != null) {
            List<AuditLog> logs=baseService.auditLogRepository.findByUsername(serviceProvider.getUsername());
            for (AuditLog log:logs){
                if(log.getTimestamp().isAfter(request.getEndTime()) ||
                        log.getTimestamp().isBefore(request.getBeginningTime())
                ){
                    logs.remove(log);
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(logs);
        } else if (endUser != null) {
            List<AuditLog> logs=baseService.auditLogRepository.findByUsername(serviceProvider.getUsername());
            for (AuditLog log:logs){
                if(log.getTimestamp().isAfter(request.getEndTime()) ||
                        log.getTimestamp().isBefore(request.getBeginningTime())
                ){
                    logs.remove(log);
                }
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(logs);
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body("there is no user registered with this email");
    }
}

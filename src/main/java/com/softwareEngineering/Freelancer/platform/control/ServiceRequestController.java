package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import com.softwareEngineering.Freelancer.platform.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceRequestController {
    @Autowired
    private ServiceRequestService serviceRequestService;
    @RequestMapping("/createNewServiceRequest")
    public void createNewServiceRequest(@RequestBody ServiceRequest request){
        serviceRequestService.createNewServiceRequest(request);
    }
}

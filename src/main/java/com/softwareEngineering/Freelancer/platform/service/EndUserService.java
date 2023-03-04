package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserService {
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;
    @Autowired
    private EndUserRepository endUserRepository;

    public void createNewServiceRequestForEndUser(RequestForCreatingNewServiceRequest request) {
        ServiceRequest ticket=new ServiceRequest(request.getTaskType(),request.getRequirementDescriptions(),
                request.getTechnicalConstraints(),request.getDeliveryTime());
       EndUser endUser= endUserRepository.findByUsername(request.getUsername());
       endUser.getServiceRequests().add(ticket);
       endUserRepository.save(endUser);

    }
    public void signUpAsEndUser(SignUpRequest request){
       EndUser endUser= new EndUser(request.getUsername(),request.getEmail());
        endUserRepository.save(endUser);
    }
}

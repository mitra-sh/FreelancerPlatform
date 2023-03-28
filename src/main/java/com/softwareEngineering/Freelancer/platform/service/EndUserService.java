package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.repository.ServiceProviderRepository;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import com.softwareEngineering.Freelancer.platform.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EndUserService {
    @Autowired
    private EndUserRepository endUserRepository;

    public void createNewServiceRequestForEndUser(RequestForCreatingNewServiceRequest request) {
        ServiceRequest ticket=new ServiceRequest(request.getCategories(),request.getTaskType(),request.getRequirementDescriptions(),
                request.getTechnicalConstraints(),request.getDeliveryTime(),request.getRequiredSkills());
       EndUser endUser= endUserRepository.findByUsername(request.getUsername());
       endUser.getServiceRequests().add(ticket);
       endUserRepository.save(endUser);

    }
    public void signUpAsEndUser(SignUpRequest request){
       EndUser endUser= new EndUser(request.getUsername(),request.getEmail(),request.getFirstName(),request.getLastName());
        endUserRepository.save(endUser);
    }

}

package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EndUserService {
    @Autowired
    private EndUserRepository endUserRepository;

    public void deleteEndUser(EndUser endUser){
        endUserRepository.delete(endUser);
    }
    public void createNewEndUser(CreateAccountRequest request) {
        EndUser endUser = new EndUser(request.getUsername(), request.getEmail(),
                request.getFirstname(), request.getLastname(),request.getPassword(),request.getType());
        if(endUserRepository.findByUsername(endUser.getUsername())==null) {
            endUserRepository.save(endUser);
        }
        else throw new RuntimeException("The username is taken. you must use a unique username");
    }

    public EndUser createNewServiceRequestForEndUser(RequestForCreatingNewServiceRequest request) {
        ServiceRequest ticket = new ServiceRequest(request.getCategories(), request.getTaskType(), request.getRequirementDescriptions(),
                request.getTechnicalConstraints(), request.getDeliveryTime(), request.getRequiredSkills());
        EndUser endUser = endUserRepository.findByUsername(request.getUsername());
        endUser.getServiceRequests().add(ticket);
        int numberOfRequestedTask=endUser.getNumberOfRequestedTask();
        endUser.setNumberOfRequestedTask(++numberOfRequestedTask);
        endUserRepository.save(endUser);
        return endUser;
    }

    public EndUser findEndUserByUsername(String username) {
       return endUserRepository.findByUsername(username);
    }
    public EndUser findEndUserByEmailAndPassword(String email, String password) {
        return endUserRepository.findByEmailAndPassword(email,password);
    }
    public EndUser findEndUserByEmail(String email) {
        return endUserRepository.findByEmail(email);
    }
}

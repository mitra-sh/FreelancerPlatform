package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.EndUserRepository;
import com.softwareEngineering.Freelancer.platform.repository.ServiceProviderRepository;
import com.softwareEngineering.Freelancer.platform.request.MatchProviderRequest;
import com.softwareEngineering.Freelancer.platform.request.RequestForCreatingNewServiceRequest;
import com.softwareEngineering.Freelancer.platform.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EndUserService {
    @Autowired
    private EndUserRepository endUserRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public void createNewServiceRequestForEndUser(RequestForCreatingNewServiceRequest request) {
        ServiceRequest ticket=new ServiceRequest(request.getCategories(),request.getTaskType(),request.getRequirementDescriptions(),
                request.getTechnicalConstraints(),request.getDeliveryTime());
       EndUser endUser= endUserRepository.findByUsername(request.getUsername());
       endUser.getServiceRequests().add(ticket);
       endUserRepository.save(endUser);

    }
    public void signUpAsEndUser(SignUpRequest request){
       EndUser endUser= new EndUser(request.getUsername(),request.getEmail());
        endUserRepository.save(endUser);
    }

    public HashMap<ServiceProvider, Integer> findTheMostMatchedServiceProvider(MatchProviderRequest request) {
        HashMap<ServiceProvider, Integer> serviceProviderMap = new HashMap<>();
        for (String item: request.characteristics) {
            List<ServiceProvider> _serviceProviders = Stream.of(serviceProviderRepository.findByCategory(item), serviceProviderRepository.findBySkill(item)).flatMap(Collection::stream).toList();
            for (ServiceProvider serviceProvider: _serviceProviders) {
                serviceProviderMap.put(serviceProvider, serviceProviderMap.getOrDefault(serviceProvider, 0)+1);
            }
        }
        return serviceProviderMap;
    }
}

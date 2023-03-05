package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.Skill;
import com.softwareEngineering.Freelancer.platform.repository.ServiceProviderRepository;
import com.softwareEngineering.Freelancer.platform.repository.SkillRepository;
import com.softwareEngineering.Freelancer.platform.request.CreateServiceProviderProfileRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderRatingRequest;
import com.softwareEngineering.Freelancer.platform.request.ServiceProviderSkillUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;
    @Autowired
    private SkillRepository skillRepository;

    public void addServiceProvider(CreateServiceProviderProfileRequest request) {
        List<Skill> skillList = new ArrayList<Skill>();
        for (String title : request.getSkills()) {
            skillList.add(new Skill(title));
        }
        ServiceProvider serviceProvider = new ServiceProvider(request.getUsername(), request.getEmail(), skillList);
        serviceProviderRepository.save(serviceProvider);
    }

    public List<ServiceProvider> showAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    @Transactional
    public void updateServiceProviderSkills(ServiceProviderSkillUpdateRequest request){
        ServiceProvider serviceProvider=serviceProviderRepository.findByUsername(request.getUsername());
        List<Skill> newSkillSet=new ArrayList<>();
        for (String newSkillTitle:request.getSkills()){
            Skill skill = new Skill(newSkillTitle);
            newSkillSet.add(skill);
        }
        serviceProvider.setSkills(newSkillSet);
        serviceProviderRepository.save(serviceProvider);
    }
    @Transactional
    public void rateAServiceProvider(ServiceProviderRatingRequest request){
        ServiceProvider serviceProvider=serviceProviderRepository.findByUsername(request.getServiceProviderUsername());
        if(serviceProvider!= null){
            int currentNumberOfRaters=serviceProvider.getNumberOfRaters();
            serviceProvider.setNumberOfRaters(++currentNumberOfRaters);

            double currentRateOfServiceProvider=serviceProvider.getRate();
            double sumOfRates=currentRateOfServiceProvider+request.getRate();
            serviceProvider.setRate(sumOfRates/serviceProvider.getNumberOfRaters());
            serviceProviderRepository.save(serviceProvider);
        }
    }
}

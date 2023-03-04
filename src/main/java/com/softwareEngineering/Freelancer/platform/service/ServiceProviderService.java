package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.Skill;
import com.softwareEngineering.Freelancer.platform.model.User;
import com.softwareEngineering.Freelancer.platform.repository.ServiceProviderRepository;
import com.softwareEngineering.Freelancer.platform.repository.SkillRepository;
import com.softwareEngineering.Freelancer.platform.request.CreateServiceProviderProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;
    @Autowired
    private SkillRepository skillRepository;

    public void addServiceProvider(CreateServiceProviderProfileRequest request){
        //add skills
        List<Skill> skillList=new ArrayList<Skill>();
        for(String title:request.getSkills()){
            skillList.add(new Skill(title));
        }
        ServiceProvider serviceProvider=new ServiceProvider(request.getUsername(),request.getEmail(),skillList);
        serviceProviderRepository.save(serviceProvider);
    }
    public List<ServiceProvider> showAllServiceProviders(){
        return serviceProviderRepository.findAll();
    }
}

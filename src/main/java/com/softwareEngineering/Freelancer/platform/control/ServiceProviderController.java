package com.softwareEngineering.Freelancer.platform.control;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import com.softwareEngineering.Freelancer.platform.model.User;
import com.softwareEngineering.Freelancer.platform.request.CreateServiceProviderProfileRequest;
import com.softwareEngineering.Freelancer.platform.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceProviderController {
    @Autowired
    private ServiceProviderService serviceProviderService;

    @RequestMapping("/createServiceProviderProfile")
    public void createServiceProviderProfile(@RequestBody CreateServiceProviderProfileRequest request){
        serviceProviderService.addServiceProvider(request);

    }
    @RequestMapping("/showAllServiceProviders")
    public String showAllServiceProviders(){
        return serviceProviderService.showAllServiceProviders().toString();
    }
}

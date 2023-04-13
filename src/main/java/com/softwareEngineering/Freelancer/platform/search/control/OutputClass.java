package com.softwareEngineering.Freelancer.platform.search.control;

import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import lombok.Getter;
import lombok.Setter;

public class OutputClass {
    @Setter @Getter
    private ServiceRequest serviceRequest;
    @Setter @Getter
    private String usernameOfProvider;

    public OutputClass(ServiceRequest serviceRequest, String usernameOfProvider) {
        this.serviceRequest = serviceRequest;
        this.usernameOfProvider = usernameOfProvider;
    }
}

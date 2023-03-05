package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class ServiceProviderRatingRequest {
    @Setter @Getter
    private String username;
    @Setter @Getter
    private String serviceProviderUsername;
    @Setter @Getter
    private int rate;
}

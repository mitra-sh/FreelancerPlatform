package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CreateServiceProviderProfileRequest {
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private List<String> skills;
    @Setter
    @Getter
    private List<String> categories;
}

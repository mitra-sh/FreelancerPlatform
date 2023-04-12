package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class ViewServiceProviderTicketsRequest {
    @Setter
    @Getter
    private String usernameOfServiceProvider;
    @Setter
    @Getter
    private String myUsername;
}

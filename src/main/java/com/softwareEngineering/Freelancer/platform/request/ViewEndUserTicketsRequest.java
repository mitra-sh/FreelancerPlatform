package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class ViewEndUserTicketsRequest {
    @Setter
    @Getter
    private String usernameOfEndUser;
    @Setter
    @Getter
    private String myUsername;
}

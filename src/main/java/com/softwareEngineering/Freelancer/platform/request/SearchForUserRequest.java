package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class SearchForUserRequest {
    @Setter
    @Getter
    private String username;
    @Setter @Getter
    private String myUsername;
}

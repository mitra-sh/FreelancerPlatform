package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Setter @Getter
    String username;
    @Setter @Getter
    String password;
}

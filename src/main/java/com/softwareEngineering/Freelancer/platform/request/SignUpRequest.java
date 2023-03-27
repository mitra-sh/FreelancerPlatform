package com.softwareEngineering.Freelancer.platform.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class SignUpRequest {
    @Setter
    @Getter
    public String username;
    @Setter
    @Getter
    public String email;
    //to be added
}

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
    @Setter
    @Getter
    public String firstName;
    @Setter
    @Getter
    public String lastName;
}

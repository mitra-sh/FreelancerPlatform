package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

public class CreateAccountRequest {
    @Setter
    @Getter
    public String username;
    @Setter
    @Getter
    public String email;
    @Setter
    @Getter
    public String firstname;
    @Setter
    @Getter
    public String lastname;
    @Setter
    @Getter
    public String password;
    @Setter
    @Getter
    public String type;
}

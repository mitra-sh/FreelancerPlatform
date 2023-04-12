package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ServiceProviderSkillUpdateRequest {
    @Setter@Getter
    private String username;
    @Setter@Getter
    private List<String> skills;

    @Override
    public String toString() {
        return "ServiceProviderSkillUpdateRequest{" +
                "username='" + username + '\'' +
                ", skills=" + skills +
                '}';
    }
}

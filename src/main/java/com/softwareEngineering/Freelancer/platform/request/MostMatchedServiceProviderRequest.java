package com.softwareEngineering.Freelancer.platform.request;

import com.softwareEngineering.Freelancer.platform.model.Category;
import com.softwareEngineering.Freelancer.platform.model.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MostMatchedServiceProviderRequest {
    @Setter @Getter
    public List<String> skills;
    @Setter @Getter
    public List<String> categories;
}

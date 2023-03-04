package com.softwareEngineering.Freelancer.platform.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class RequestForCreatingNewServiceRequest {
    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String taskType;
    @Setter
    @Getter
    private String requirementDescriptions;
    @Setter
    @Getter
    private String technicalConstraints;
    @Setter
    @Getter
    private LocalDate deliveryTime;
}

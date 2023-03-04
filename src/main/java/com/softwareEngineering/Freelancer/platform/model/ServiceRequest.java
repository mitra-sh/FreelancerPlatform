package com.softwareEngineering.Freelancer.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "serviceRequests")
public class ServiceRequest {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column
    private String taskType;
    @Setter
    @Getter
    @Column
    private String requirementDescriptions;
    @Setter
    @Getter
    @Column
    private String technicalConstraints;
    @Setter
    @Getter
    @Column
    private LocalDate deliveryTime;
    @Setter
    @Getter
    @ManyToMany(mappedBy = "serviceRequests")
    private List<EndUser> endUsers;

    public ServiceRequest(String taskType, String requirementDescriptions, String technicalConstraints, LocalDate deliveryTime) {
        this.taskType = taskType;
        this.requirementDescriptions = requirementDescriptions;
        this.technicalConstraints = technicalConstraints;
        this.deliveryTime = deliveryTime;
    }

    public ServiceRequest() {}
}

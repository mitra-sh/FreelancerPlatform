package com.softwareEngineering.Freelancer.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
}

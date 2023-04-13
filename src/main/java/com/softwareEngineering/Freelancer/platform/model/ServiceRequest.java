package com.softwareEngineering.Freelancer.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column
    private List<String> requiredSkills;
    @Setter
    @Getter
    @Column
    private String status="not_taken";
    @Setter
    @Getter
    @Column
    private String usernameOfProvider="";

    @Setter
    @Getter
    @ManyToMany(mappedBy = "serviceRequests")
    private List<EndUser> endUsers;
    @Setter
    @Getter
    @JsonIgnore
    @ManyToMany(mappedBy = "serviceRequests")
    private List<ServiceProvider> serviceProviders;
    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)

    @JoinTable(name = "serviceRequest_categories",
            joinColumns = @JoinColumn(name = "serviceRequest_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private List<Category> relatedCategories;

    public ServiceRequest(List<Category> categories,String taskType, String requirementDescriptions,
                          String technicalConstraints, LocalDate deliveryTime,List<String> requiredSkills) {
        this.taskType = taskType;
        this.requirementDescriptions = requirementDescriptions;
        this.technicalConstraints = technicalConstraints;
        this.deliveryTime = deliveryTime;
        this.relatedCategories=categories;
        this.setRequiredSkills(requiredSkills);
        this.setStatus("not_taken");
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "id=" + id +
                ", taskType='" + taskType + '\'' +
                ", requirementDescriptions='" + requirementDescriptions + '\'' +
                ", technicalConstraints='" + technicalConstraints + '\'' +
                ", deliveryTime=" + deliveryTime.toString() +
                ", requiredSkills=" + requiredSkills.toString() +
                ", relatedCategories=" + relatedCategories.toString() +

                '}';
    }

    public ServiceRequest() {

    }
}

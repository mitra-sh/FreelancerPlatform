package com.softwareEngineering.Freelancer.platform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "endUsers")
public class EndUser extends User {
    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "endUser_ServiceRequest",
            joinColumns = @JoinColumn(name = "endUser_id"),
            inverseJoinColumns = @JoinColumn(name = "serviceRequest_id"))
    private List<ServiceRequest> serviceRequests;

    @Setter
    @Getter
    public int numberOfRequestedTask = 0;

    public EndUser(String username, String email, String firstName, String lastName, String password, String type) {
        this.setUsername(username);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setType(type);
        this.serviceRequests = new ArrayList<ServiceRequest>();
    }


    public EndUser() {
        this.serviceRequests = new ArrayList<ServiceRequest>();
    }

    @Override
    public String toString() {
        return "EndUser{" +
                "serviceRequests=" + serviceRequests +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

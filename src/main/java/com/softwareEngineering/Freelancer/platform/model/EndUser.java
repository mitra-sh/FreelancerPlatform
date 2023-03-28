package com.softwareEngineering.Freelancer.platform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name ="endUsers")
public class EndUser extends User{
    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "endUser_ServiceRequest",
            joinColumns = @JoinColumn(name = "endUser_id"),
            inverseJoinColumns = @JoinColumn(name = "serviceRequest_id"))
    private List<ServiceRequest> serviceRequests;

    public EndUser(String username,String email,String firstName,String lastName) {
       this.setUsername(username);
       this.setEmail(email);
       this.setFirstName(firstName);
       this.setLastName(lastName);
        this.serviceRequests = new ArrayList<ServiceRequest>();
    }
    public EndUser() {
        this.serviceRequests = new ArrayList<ServiceRequest>();
    }
}

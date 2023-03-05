package com.softwareEngineering.Freelancer.platform.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name ="serviceProviders")
public class ServiceProvider extends User {
    @Setter
    @Getter
    @Column
    private double rate;
    @Setter
    @Getter
    @Column
    private int numberOfRaters;
    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "serviceProvider_skills",
            joinColumns = @JoinColumn(name = "serviceProvider_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;


    public ServiceProvider(String username,String email,List<Skill> skills) {
        this.setUsername(username);
        this.setEmail(email);
        this.skills = skills;
        this.setRate(0);
        this.setNumberOfRaters(0);
    }

    public ServiceProvider(){
        this.setRate(0);
        this.setNumberOfRaters(0);
    }

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "rate=" + rate +
                ", numberOfRaters=" + numberOfRaters +
                ", skills=" + skills +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

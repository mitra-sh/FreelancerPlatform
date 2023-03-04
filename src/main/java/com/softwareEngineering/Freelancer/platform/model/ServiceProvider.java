package com.softwareEngineering.Freelancer.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="serviceProviders")
public class ServiceProvider extends User {
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
    }

    public ServiceProvider(){}

    @Override
    public String toString() {
        return "ServiceProvider{" +
                "skills=" + skills +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

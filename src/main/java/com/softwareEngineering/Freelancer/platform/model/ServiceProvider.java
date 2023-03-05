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
    private double rate=0;
    @Setter
    @Getter
    @Column
    private int numberOfRaters=0;
    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "serviceProvider_skills",
            joinColumns = @JoinColumn(name = "serviceProvider_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "serviceProvider_categories",
            joinColumns = @JoinColumn(name = "serviceProvider_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;


    public ServiceProvider(String username,String email,List<Skill> skills,List<Category> categories) {
        this.setUsername(username);
        this.setEmail(email);
        this.skills = skills;
        this.setCategories(categories);
    }

    public ServiceProvider(){
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

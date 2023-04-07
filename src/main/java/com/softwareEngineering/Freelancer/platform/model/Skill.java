package com.softwareEngineering.Freelancer.platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skills")
public class Skill {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @Column
    private String skillTitle;

    @Setter
    @Getter
    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    private List<ServiceProvider> serviceProviders;

    public Skill(String skillTitle) {
        this.skillTitle = skillTitle;
    }
    public Skill(){}

    @Override
    public String toString() {
        return "Skill{" +
                "skillTitle='" + skillTitle + '\'' +
                '}';
    }
}

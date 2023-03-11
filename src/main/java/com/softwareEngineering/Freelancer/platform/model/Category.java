package com.softwareEngineering.Freelancer.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @Setter
    @Getter
    private List<ServiceProvider> serviceProviders=new ArrayList<ServiceProvider>();

    @ManyToMany(mappedBy = "relatedCategories")
    @Setter
    @Getter
    private List<ServiceRequest> serviceRequests=new ArrayList<ServiceRequest>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category() {}

    @Override
    public String toString() {
        return "categoryName='" + categoryName + '\'';
    }
}

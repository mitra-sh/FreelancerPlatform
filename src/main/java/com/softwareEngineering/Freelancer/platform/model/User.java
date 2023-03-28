package com.softwareEngineering.Freelancer.platform.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;
    @Setter
    @Getter
    @Column
    public String username;
    @Setter
    @Getter
    @Column
    public String email;
    @Setter
    @Getter
    @Column
    public String firstName;
    @Setter
    @Getter
    @Column
    public String lastName;
}

package com.softwareEngineering.Freelancer.platform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column (length = 2000)
    private String details;


    public AuditLog(String action, LocalDateTime timestamp, String details, String username) {
        this.action = action;
        this.timestamp = timestamp;
        this.details = details;
        this.username=username;
    }

    public AuditLog() {}

}

package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.AuditLog;
import com.softwareEngineering.Freelancer.platform.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void log(String username,String action, String details ) {
        AuditLog auditLog = new AuditLog(action, LocalDateTime.now(), details, username);
        auditLogRepository.save(auditLog);
    }
}

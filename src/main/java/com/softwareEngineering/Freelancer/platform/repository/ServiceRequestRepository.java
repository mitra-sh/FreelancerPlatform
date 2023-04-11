package com.softwareEngineering.Freelancer.platform.repository;

import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {
    ServiceRequest findByTicketNumber(Long id);

}

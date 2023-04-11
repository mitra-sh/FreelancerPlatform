package com.softwareEngineering.Freelancer.platform.service;

import com.softwareEngineering.Freelancer.platform.model.ServiceRequest;
import com.softwareEngineering.Freelancer.platform.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {
    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public List<ServiceRequest> viewAllTickets(){
        return serviceRequestRepository.findAll();
    }
    public ServiceRequest findTicketByTicketNumber(int id){
        return  serviceRequestRepository.findByTicketNumber(Long.valueOf(id));
    }
}

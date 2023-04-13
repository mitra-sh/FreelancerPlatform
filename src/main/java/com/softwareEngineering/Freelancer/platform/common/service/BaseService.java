package com.softwareEngineering.Freelancer.platform.common.service;

import com.softwareEngineering.Freelancer.platform.common.EmailServiceHelper;
import com.softwareEngineering.Freelancer.platform.common.repository.*;
import com.softwareEngineering.Freelancer.platform.model.*;
import com.softwareEngineering.Freelancer.platform.request.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BaseService {
    @Autowired
    public AuditLogRepository auditLogRepository;
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public EndUserRepository endUserRepository;
    @Autowired
    public ServiceProviderRepository serviceProviderRepository;
    @Autowired
    public ServiceRequestRepository serviceRequestRepository;
    @Autowired
    public SkillRepository skillRepository;

   public List<AuditLog> findLogFilteredByUsername(String username){
        return auditLogRepository.findByUsername(username);
   }
    public  List<ServiceRequest> findAllTickets(){
        return serviceRequestRepository.findAll();
    }

    public ServiceRequest updateServiceRequestRepository(long id){
       ServiceRequest serviceRequest= findServiceRequestById(id);
       if(serviceRequest!=null){
           serviceRequest.setStatus("completed");
           serviceRequestRepository.save(serviceRequest);
           return serviceRequest;
       }
       else throw new RuntimeException("invalid request id");
   }
    public void log(String username,String action, String details ) {
        AuditLog auditLog = new AuditLog(action, LocalDateTime.now(), details, username);
        auditLogRepository.save(auditLog);
    }
    public ServiceRequest findServiceRequestById(long id){
        Optional<ServiceRequest> optionalServiceRequest= serviceRequestRepository.findById(id);
        if (optionalServiceRequest.isPresent()) {
            return optionalServiceRequest.get();
        } else {
            throw new EntityNotFoundException("ServiceRequest not found with ID: " + id);
        }
    }
    public String showAllCategories(){
        return categoryRepository.findAllDistinctCategories().toString();
    }
    public static void sendInvitation(String recipientEmail) throws MessagingException {
        EmailServiceHelper.sendInvitation(recipientEmail, "freelancer.platform.6311@gmail.com", "wuqnufztuwxrkvak");
        System.out.println("Invitation email sent successfully.");
    }
    public void deleteEndUser(EndUser endUser){
        endUserRepository.delete(endUser);
    }
    public void createNewEndUser(CreateAccountRequest request) {
        EndUser endUser = new EndUser(request.getUsername(), request.getEmail(),
                request.getFirstname(), request.getLastname(),request.getPassword(),request.getType());
        if(endUserRepository.findByUsername(endUser.getUsername())==null) {
            endUserRepository.save(endUser);
        }
        else throw new RuntimeException("The username is taken. you must use a unique username");
    }

    public EndUser createNewServiceRequestForEndUser(RequestForCreatingNewServiceRequest request) {
        ServiceRequest ticket = new ServiceRequest(request.getCategories(), request.getTaskType(), request.getRequirementDescriptions(),
                request.getTechnicalConstraints(), request.getDeliveryTime(), request.getRequiredSkills());
        EndUser endUser = endUserRepository.findByUsername(request.getUsername());
        endUser.getServiceRequests().add(ticket);
        int numberOfRequestedTask=endUser.getNumberOfRequestedTask();
        endUser.setNumberOfRequestedTask(++numberOfRequestedTask);
        endUserRepository.save(endUser);
        return endUser;
    }

    public EndUser findEndUserByUsername(String username) {
        return endUserRepository.findByUsername(username);
    }
    public EndUser findEndUserByEmailAndPassword(String email, String password) {
        return endUserRepository.findByEmailAndPassword(email,password);
    }
    public EndUser findEndUserByEmail(String email) {
        return endUserRepository.findByEmail(email);
    }
    public void createNewServiceProvider(CreateAccountRequest request){
        ServiceProvider serviceProvider=new ServiceProvider(request.getUsername(),request.getEmail(),
                request.getFirstname(),request.getLastname(),request.getPassword(),request.getType());
        if(serviceProviderRepository.findByUsername(serviceProvider.getUsername())==null) {
            serviceProviderRepository.save(serviceProvider);
        }
        else throw new RuntimeException("The username is taken. you must use a unique username");
    }
    public ServiceProvider findServiceProviderByEmailAndPassword(String email,String password){
        return serviceProviderRepository.findByEmailAndPassword(email,password);
    }
    public ServiceProvider findServiceProviderByUsername(String username){
        ServiceProvider serviceProvider=serviceProviderRepository.findByUsername(username);
        return serviceProvider;
    }
    public ServiceProvider findServiceProviderByEmail(String email){
        return serviceProviderRepository.findByEmail(email);
    }

    public void deleteServiceProvider(ServiceProvider serviceProvider){
        serviceProviderRepository.delete(serviceProvider);
    }
    public void addServiceProvider(CreateServiceProviderProfileRequest request) {
        List<Skill> skillList = new ArrayList<Skill>();
        List<Category> categoryList = new ArrayList<Category>();
        for (String title : request.getSkills()) {
            skillList.add(new Skill(title));
        }
        for (String categoryName : request.getCategories()) {
            categoryList.add(new Category(categoryName));
        }
        ServiceProvider serviceProvider = new ServiceProvider(request.getUsername(), request.getEmail(), skillList, categoryList);
        serviceProviderRepository.save(serviceProvider);
    }

    public List<ServiceProvider> showAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    @Transactional
    public ServiceProvider updateServiceProviderSkills(ServiceProviderSkillUpdateRequest request) {
        ServiceProvider serviceProvider = serviceProviderRepository.findByUsername(request.getUsername());
        List<Skill> newSkillSet = new ArrayList<>();
        for (String newSkillTitle : request.getSkills()) {
            Skill skill = new Skill(newSkillTitle);
            newSkillSet.add(skill);
        }
        serviceProvider.setSkills(newSkillSet);
        serviceProviderRepository.save(serviceProvider);
        return serviceProvider;
    }

    @Transactional
    public void rateAServiceProvider(ServiceProviderRatingRequest request) {
        ServiceProvider serviceProvider = serviceProviderRepository.findByUsername(request.getServiceProviderUsername());
        if (serviceProvider != null) {
            int currentNumberOfRaters = serviceProvider.getNumberOfRaters();
            serviceProvider.setNumberOfRaters(++currentNumberOfRaters);

            double currentRateOfServiceProvider = serviceProvider.getRate();
            double sumOfRates = currentRateOfServiceProvider + request.getRate();
            serviceProvider.setRate(sumOfRates / serviceProvider.getNumberOfRaters());
            serviceProviderRepository.save(serviceProvider);
        }
    }

    public ServiceProvider findTheMostMatchedServiceProviderForAUser(String username) {
        EndUser endUser=endUserRepository.findByUsername(username);
        List<ServiceProvider> listOfServiceProviders = new ArrayList<ServiceProvider>();
        HashMap<String,ServiceProvider> finalList=new HashMap<String,ServiceProvider>();
        //probably might not work for two
        for (ServiceRequest serviceRequest : endUser.getServiceRequests()) {
            for (String skillTitle : serviceRequest.getRequiredSkills()) {
                listOfServiceProviders = serviceProviderRepository.findBySkillsSkillTitle(skillTitle);
                if (listOfServiceProviders != null) {
                    for (ServiceProvider serviceProvider : listOfServiceProviders) {
                        int numberOfMatchedSkills = serviceProvider.getNumberOfMatchedSkills();
                        numberOfMatchedSkills++;
                        serviceProvider.setNumberOfMatchedSkills(numberOfMatchedSkills);
                        finalList.put(serviceProvider.getUsername(),serviceProvider);
                    }
                } else
                    throw new RuntimeException("No service provider is found to match the required skills of the service ticket");
            }
        }
        Comparator<ServiceProvider> byRateDesc = Comparator.comparing(ServiceProvider::getNumberOfMatchedSkills, Comparator.reverseOrder());
        //Collections.sort(listOfServiceProviders, byRateDesc);
        List<ServiceProvider> finall = new ArrayList<ServiceProvider>();
        for (ServiceProvider serviceProvider:finalList.values()){
            finall.add(serviceProvider);
        }
        Collections.sort(finall, byRateDesc);
        return listOfServiceProviders.get(0);
    }
    public ServiceProvider acceptTicket(String username, long ticketId){
        ServiceProvider serviceProvider=serviceProviderRepository.findByUsername(username);
        Optional<ServiceRequest> optionalServiceRequest= serviceRequestRepository.findById(ticketId);
        ServiceRequest serviceRequest;
        if (optionalServiceRequest.isPresent()) {
            serviceRequest= optionalServiceRequest.get();
        } else {
            throw new RuntimeException("ServiceRequest not found ");
        }
        int numberOfServedTask= serviceProvider.getNumberOfServedTask();
        serviceProvider.setNumberOfServedTask(++numberOfServedTask);
        serviceRequest.setStatus("taken");
        serviceRequest.setUsernameOfProvider(serviceProvider.getUsername());
        if(serviceProvider.getServiceRequests()!=null) {
            serviceProvider.getServiceRequests().add(serviceRequest);
        }
        else {
            List<ServiceRequest> tickets=new ArrayList<ServiceRequest>();
            tickets.add(serviceRequest);
            serviceProvider.setServiceRequests(tickets);
        }
        serviceProviderRepository.save(serviceProvider);
        return serviceProvider;
    }
    public List<ServiceRequest> viewAllTickets() {
        return serviceRequestRepository.findAll();
    }

}

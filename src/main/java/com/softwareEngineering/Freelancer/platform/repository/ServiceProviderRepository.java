package com.softwareEngineering.Freelancer.platform.repository;

import com.softwareEngineering.Freelancer.platform.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Long> {
    ServiceProvider findByUsername(String username);
    ServiceProvider findByEmail(String email);
    ServiceProvider findByEmailAndPassword(String email,String password);
    List<ServiceProvider> findBySkillsSkillTitle(String skillTitle);
}

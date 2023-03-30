package com.softwareEngineering.Freelancer.platform.repository;

import com.softwareEngineering.Freelancer.platform.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser,Long> {
    EndUser findByUsername(String username);
    EndUser findByEmail(String email);

}

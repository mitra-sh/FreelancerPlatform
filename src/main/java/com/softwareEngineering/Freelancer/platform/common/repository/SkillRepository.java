package com.softwareEngineering.Freelancer.platform.common.repository;

import com.softwareEngineering.Freelancer.platform.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository< Skill,Long> {
     Skill findBySkillTitle(String skillTitle);
}

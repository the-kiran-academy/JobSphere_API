package com.tka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.entity.UserSkill;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    boolean existsByUserIdAndSkillId(Long userId, Long skillId);
}
package com.tka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tka.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByName(String name);
    boolean existsByName(String name);
}
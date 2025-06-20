package com.tka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
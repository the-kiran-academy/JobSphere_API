package com.tka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
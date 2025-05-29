package com.tka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tka.entity.JobView;

public interface JobViewRepository extends JpaRepository<JobView, Long> {
    @Query("SELECT COUNT(jv) FROM JobView jv WHERE jv.job.id = :jobId")
    long countByJobId(@Param("jobId") Long jobId);
}
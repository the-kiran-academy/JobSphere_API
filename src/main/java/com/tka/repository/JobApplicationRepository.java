package com.tka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tka.entity.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	List<JobApplication> findByJobId(Long jobId);

	List<JobApplication> findByUserId(Long userId);

	@Query("SELECT COUNT(ja) FROM JobApplication ja WHERE ja.job.id = :jobId")
	long countByJobId(@Param("jobId") Long jobId);
}
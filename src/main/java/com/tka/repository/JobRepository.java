package com.tka.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tka.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query("SELECT DISTINCT j FROM Job j "
			+ "WHERE :keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Job> searchByTitle(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT DISTINCT j FROM Job j JOIN j.location l "
			+ "WHERE :location IS NULL OR LOWER(l.city) LIKE LOWER(CONCAT('%', :location, '%')) "
			+ "OR LOWER(l.state) LIKE LOWER(CONCAT('%', :location, '%')) "
			+ "OR LOWER(l.country) LIKE LOWER(CONCAT('%', :location, '%'))")
	Page<Job> searchByLocation(@Param("location") String location, Pageable pageable);

	@Query("SELECT DISTINCT j FROM Job j " + "WHERE (:minSalary IS NULL AND :maxSalary IS NULL) OR "
			+ "(j.minSalary <= :maxSalary AND j.maxSalary >= :minSalary)")
	Page<Job> searchBySalary(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary,
			Pageable pageable);

	@Query("SELECT DISTINCT j FROM Job j JOIN j.skills s " +
		       "WHERE LOWER(s.name) IN :skillNames")
		Page<Job> searchBySkills(@Param("skillNames") List<String> skillNames, Pageable pageable);


	@Query("SELECT j FROM Job j JOIN j.skills s "
			+ "WHERE s.id IN (SELECT us.skill.id FROM UserSkill us WHERE us.user.id = :userId)")
	Page<Job> findRecommendedJobs(@Param("userId") Long userId, Pageable pageable);
}
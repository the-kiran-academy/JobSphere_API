package com.tka.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tka.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j " +
           "JOIN j.skills s " +
           "WHERE (:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:categoryId IS NULL OR j.category.id = :categoryId) " +
           "AND (:locationId IS NULL OR j.location.id = :locationId) " +
           "AND (:skillIds IS NULL OR s.id IN :skillIds) " +
           "AND (:minSalary IS NULL OR j.salaryRange >= :minSalary) " +
           "AND (:maxSalary IS NULL OR j.salaryRange <= :maxSalary)")
    Page<Job> searchJobs(@Param("keyword") String keyword,
                         @Param("categoryId") Long categoryId,
                         @Param("locationId") Long locationId,
                         @Param("skillIds") List<Long> skillIds,
                         @Param("minSalary") Double minSalary,
                         @Param("maxSalary") Double maxSalary,
                         Pageable pageable);
    

    @Query("SELECT j FROM Job j JOIN j.skills s " +
           "WHERE s.id IN (SELECT us.skill.id FROM UserSkill us WHERE us.user.id = :userId)")
    Page<Job> findRecommendedJobs(@Param("userId") Long userId, Pageable pageable);
}
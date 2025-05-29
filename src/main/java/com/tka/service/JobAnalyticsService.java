package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobAnalyticsResponseDTO;
import com.tka.repository.JobApplicationRepository;
import com.tka.repository.JobViewRepository;

@Service
public class JobAnalyticsService {
    private final JobViewRepository jobViewRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @Autowired
    public JobAnalyticsService(JobViewRepository jobViewRepository, 
                              JobApplicationRepository jobApplicationRepository) {
        this.jobViewRepository = jobViewRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobAnalyticsResponseDTO getJobAnalytics(Long jobId) {
        JobAnalyticsResponseDTO dto = new JobAnalyticsResponseDTO();
        dto.setJobId(jobId);
        dto.setViews(jobViewRepository.countByJobId(jobId));
        dto.setApplications(jobApplicationRepository.countByJobId(jobId));
        return dto;
    }
}
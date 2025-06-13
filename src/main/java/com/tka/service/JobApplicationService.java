package com.tka.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobApplicationCreateDTO;
import com.tka.dtos.JobApplicationResponseDTO;
import com.tka.entity.Job;
import com.tka.entity.JobApplication;
import com.tka.entity.User;
import com.tka.enums.ApplicationStatus;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.JobApplicationRepository;
import com.tka.repository.JobRepository;
import com.tka.repository.UserRepository;

@Service
public class JobApplicationService {
	private final JobApplicationRepository jobApplicationRepository;
	private final JobRepository jobRepository;
	private final UserRepository userRepository;
	private final NotificationService notificationService;

	@Autowired
	public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobRepository jobRepository,
			UserRepository userRepository, NotificationService notificationService) {
		this.jobApplicationRepository = jobApplicationRepository;
		this.jobRepository = jobRepository;
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}

	@Transactional
	public JobApplicationResponseDTO createApplication(JobApplicationCreateDTO dto) {
		Job job = jobRepository.findById(dto.getJobId())
				.orElseThrow(() -> new ResourceNotFoundException("Job not found"));
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		
		JobApplication application = new JobApplication();
		application.setJob(job);
		application.setUser(user);
		application.setCoverLetter(dto.getCoverLetter());
		application.setStatus(ApplicationStatus.PENDING);
		application.setAppliedAt(LocalDateTime.now());
		
		application = jobApplicationRepository.save(application);
		return Mapper.mapToJobApplicationResponseDTO(application);
	}

	@Transactional
    public String updateApplicationStatus(Long id, String status, String comment) {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        application.setStatus(ApplicationStatus.valueOf(status));
        application.setComments(comment);
        jobApplicationRepository.save(application);
        String message = String.format("Your application for %s has been %s.%s", 
            application.getJob().getTitle(), status, 
            comment != null ? " Comment: " + comment : "");
        notificationService.createNotification(application.getUser().getId(), message);
        return message;
    }

	public List<JobApplicationResponseDTO> getApplicationsByJob(Long jobId) {
		return jobApplicationRepository.findByJobId(jobId).stream().map(Mapper::mapToJobApplicationResponseDTO)
				.collect(Collectors.toList());
	}

	public List<JobApplicationResponseDTO> getApplicationsByUser(Long userId) {
		return jobApplicationRepository.findByUserId(userId).stream().map(Mapper::mapToJobApplicationResponseDTO)
				.collect(Collectors.toList());
	}

	public JobApplicationResponseDTO getApplicationById(Long id) {
		JobApplication application = jobApplicationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found"));
		return application != null ? Mapper.mapToJobApplicationResponseDTO(application) : null;
	}
}
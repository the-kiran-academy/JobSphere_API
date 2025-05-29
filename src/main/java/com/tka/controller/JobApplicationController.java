package com.tka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.JobApplicationCreateDTO;
import com.tka.dtos.JobApplicationResponseDTO;
import com.tka.service.JobApplicationService;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
	private final JobApplicationService applicationService;

	@Autowired
	public JobApplicationController(JobApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@PostMapping
	public JobApplicationResponseDTO createApplication(@Valid @RequestBody JobApplicationCreateDTO dto) {
		return applicationService.createApplication(dto);
	}

	@GetMapping("/{id}")
	public JobApplicationResponseDTO getApplication(@PathVariable Long id) {
		return applicationService.getApplicationById(id);
	}

	@GetMapping
	public List<JobApplicationResponseDTO> getApplications(@RequestParam(required = false) Long jobId,
			@RequestParam(required = false) Long userId) {
		if (jobId != null) {
			return applicationService.getApplicationsByJob(jobId);
		} else if (userId != null) {
			return applicationService.getApplicationsByUser(userId);
		}
		throw new IllegalArgumentException("Provide either jobId or userId");
	}

	@PutMapping("/{id}/status")
	public String updateApplicationStatus(@PathVariable Long id, @RequestParam String status,
			@RequestParam(required = false) String comment) {
		// Assuming userId is handled in service layer
		return applicationService.updateApplicationStatus(id, status, comment);
	}
}
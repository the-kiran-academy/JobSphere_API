package com.tka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.JobAnalyticsResponseDTO;
import com.tka.dtos.JobCreateDTO;
import com.tka.dtos.JobResponseDTO;
import com.tka.service.JobAnalyticsService;
import com.tka.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
	private final JobService jobService;
	private final JobAnalyticsService jobAnalyticsService;

	@Autowired
	public JobController(JobService jobService, JobAnalyticsService jobAnalyticsService) {
		this.jobService = jobService;
		this.jobAnalyticsService = jobAnalyticsService;
	}

	@PostMapping
	public JobResponseDTO createJob(@Valid @RequestBody JobCreateDTO dto) {
		return jobService.createJob(dto, dto.getUserId());
	}

	@GetMapping("/{id}")
	public JobResponseDTO getJob(@PathVariable Long id, @RequestParam(required = false) Long userId) {
		return jobService.getJob(id, userId);
	}

	@GetMapping
	public Page<JobResponseDTO> getAllJobs(Pageable pageable) {
		return jobService.getAllJobs(pageable);
	}

	@GetMapping("/search/title")
	public ResponseEntity<Page<JobResponseDTO>> searchByTitle(@Valid @RequestParam String keyword,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<JobResponseDTO> response = jobService.searchByTitle(keyword, pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search/location")
	public ResponseEntity<Page<JobResponseDTO>> searchByLocation(@Valid @RequestParam String location,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<JobResponseDTO> response = jobService.searchByLocation(location, pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search/salary")
	public ResponseEntity<Page<JobResponseDTO>> searchBySalary(@Valid @RequestParam Double minSalary,
			@Valid @RequestParam Double maxSalary, @PageableDefault(size = 10) Pageable pageable) {
		Page<JobResponseDTO> response = jobService.searchBySalary(minSalary,maxSalary, pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search/skills")
	public ResponseEntity<Page<JobResponseDTO>> searchBySkills(@Valid @RequestParam List<String> skillNames,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<JobResponseDTO> response = jobService.searchBySkills(skillNames, pageable);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/recommended")
	public Page<JobResponseDTO> getRecommendedJobs(@RequestParam Long userId, Pageable pageable) {
		return jobService.getRecommendedJobs(userId, pageable);
	}

	@GetMapping("/{id}/analytics")
	public JobAnalyticsResponseDTO getJobAnalytics(@PathVariable Long id) {
		return jobAnalyticsService.getJobAnalytics(id);
	}
}
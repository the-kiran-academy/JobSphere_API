package com.tka.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobCreateDTO;
import com.tka.dtos.JobResponseDTO;
import com.tka.entity.Category;
import com.tka.entity.Company;
import com.tka.entity.Job;
import com.tka.entity.JobView;
import com.tka.entity.Location;
import com.tka.entity.Skill;
import com.tka.entity.User;
import com.tka.enums.EmploymentType;
import com.tka.enums.JobStatus;
import com.tka.enums.Role;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.CategoryRepository;
import com.tka.repository.CompanyRepository;
import com.tka.repository.JobRepository;
import com.tka.repository.JobViewRepository;
import com.tka.repository.LocationRepository;
import com.tka.repository.SkillRepository;
import com.tka.repository.UserRepository;
import com.tka.utils.StringUtil;

@Service
public class JobService {
	private final JobRepository jobRepository;
	private final UserRepository userRepository;
	private final CompanyRepository companyRepository;
	private final CategoryRepository categoryRepository;
	private final LocationRepository locationRepository;
	private final SkillRepository skillRepository;
	private final JobViewRepository jobViewRepository;

	@Autowired
	public JobService(JobRepository jobRepository, UserRepository userRepository, CompanyRepository companyRepository,
			CategoryRepository categoryRepository, LocationRepository locationRepository,
			SkillRepository skillRepository, JobViewRepository jobViewRepository) {
		this.jobRepository = jobRepository;
		this.userRepository = userRepository;
		this.companyRepository = companyRepository;
		this.categoryRepository = categoryRepository;
		this.locationRepository = locationRepository;
		this.skillRepository = skillRepository;
		this.jobViewRepository = jobViewRepository;
	}

	@Transactional
	public JobResponseDTO createJob(JobCreateDTO dto, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		if (!user.getRole().equals(Role.EMPLOYER)) {
			throw new IllegalArgumentException("User must be an EMPLOYER");
		}
		Company company = companyRepository.findById(dto.getCompanyId())
				.orElseThrow(() -> new ResourceNotFoundException("Company not found"));
		Category category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		Location location = locationRepository.findById(dto.getLocationId())
				.orElseThrow(() -> new ResourceNotFoundException("Location not found"));

		// Process skills
		List<Skill> skillList = new ArrayList<>();
		if (dto.getSkillNames() != null && !dto.getSkillNames().isEmpty()) {
			for (String skillName : dto.getSkillNames()) {
				if (skillName == null || skillName.trim().isEmpty()) {
					continue;
				}

				Skill skill = skillRepository.findByName(skillName.trim()).orElseGet(() -> {
					// Create new skill if it doesn't exist
					Skill newSkill = new Skill();
					newSkill.setName(StringUtil.capitalizeWords(skillName));
					return skillRepository.save(newSkill);
				});
				skillList.add(skill);
			}
		}

		Job job = new Job();
		job.setTitle(dto.getTitle());
		job.setDescription(dto.getDescription());
		job.setCompany(company);
		job.setCategory(category);
		job.setLocation(location);
		job.setMinSalary(dto.getMinSalary());
		job.setMaxSalary(dto.getMaxSalary());
		job.setEmploymentType(EmploymentType.valueOf(dto.getEmploymentType()));
		job.setPostedBy(user);
		job.setPostedAt(LocalDateTime.now());
		job.setStatus(JobStatus.OPEN);
		job.setSkills(skillList);
		job = jobRepository.save(job);
		return Mapper.mapToJobResponseDTO(job);
	}

	@Transactional
	public JobResponseDTO getJob(Long id, Long userId) {
	    Job job = jobRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

	    // Log job view if not already viewed by this user
	    if (userId != null) {
	        boolean alreadyViewed = jobViewRepository.findByJobIdAndUserId(id, userId).isPresent();
	        if (!alreadyViewed) {
	            JobView jobView = new JobView();
	            jobView.setJob(job);
	            jobView.setUser(userRepository.findById(userId).orElse(null));
	            jobView.setViewedAt(LocalDateTime.now());
	            jobViewRepository.save(jobView);
	        }
	    }

	    return Mapper.mapToJobResponseDTO(job);
	}


	public Page<JobResponseDTO> getAllJobs(Pageable pageable) {
		return jobRepository.findAll(pageable).map(Mapper::mapToJobResponseDTO);
	}

	public Page<JobResponseDTO> searchByTitle(String keyword, Pageable pageable) {
		return jobRepository.searchByTitle(keyword, pageable).map(Mapper::mapToJobResponseDTO);
	}

	public Page<JobResponseDTO> searchByLocation(String location, Pageable pageable) {
		return jobRepository.searchByLocation(location, pageable).map(Mapper::mapToJobResponseDTO);
	}

	public Page<JobResponseDTO> searchBySalary(Double minSalary, Double maxSalary, Pageable pageable) {
		return jobRepository.searchBySalary(minSalary, maxSalary, pageable).map(Mapper::mapToJobResponseDTO);
	}

	public Page<JobResponseDTO> searchBySkills(List<String> skillNames, Pageable pageable) {

		if (skillNames == null || skillNames.isEmpty()) {
			throw new IllegalArgumentException("Skill names must not be null or empty");
		}

		List<String> lowerCaseSkills = skillNames.stream().map(String::toLowerCase).collect(Collectors.toList());

		return jobRepository.searchBySkills(lowerCaseSkills, pageable).map(Mapper::mapToJobResponseDTO);
	}

	public Page<JobResponseDTO> getRecommendedJobs(Long userId, Pageable pageable) {
		// Placeholder for recommended jobs logic
		return jobRepository.findAll(pageable).map(Mapper::mapToJobResponseDTO);
	}

}
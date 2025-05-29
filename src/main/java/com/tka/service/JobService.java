package com.tka.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobCreateDTO;
import com.tka.dtos.JobResponseDTO;
import com.tka.dtos.JobSearchDTO;
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
    public JobService(JobRepository jobRepository, UserRepository userRepository,
                      CompanyRepository companyRepository, CategoryRepository categoryRepository,
                      LocationRepository locationRepository, SkillRepository skillRepository,
                      JobViewRepository jobViewRepository) {
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
                    continue; // Skip empty or null skill names
                }
                // Check if skill exists
                Skill skill = skillRepository.findByName(skillName.trim())
                        .orElseGet(() -> {
                            // Create new skill if it doesn't exist
                            Skill newSkill = new Skill();
                            newSkill.setName(skillName.trim());
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
        job.setSalaryRange(dto.getSalaryRange());
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
        
        // Log job view
        JobView jobView = new JobView();
        jobView.setJob(job);
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElse(null);
            jobView.setUser(user);
        }
        jobView.setViewedAt(LocalDateTime.now());
        jobViewRepository.save(jobView);

        return Mapper.mapToJobResponseDTO(job);
    }

    public Page<JobResponseDTO> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable)
                .map(Mapper::mapToJobResponseDTO);
    }

    public Page<JobResponseDTO> searchJobs(JobSearchDTO dto, Pageable pageable) {
        return jobRepository.searchJobs(
                dto.getKeyword(),
                dto.getCategoryId(),
                dto.getLocationId(),
                dto.getSkillIds(),
                dto.getMinSalary(),
                dto.getMaxSalary(),
                pageable
        ).map(Mapper::mapToJobResponseDTO);
    }

    public Page<JobResponseDTO> getRecommendedJobs(Long userId, Pageable pageable) {
        return jobRepository.findRecommendedJobs(userId, pageable)
                .map(Mapper::mapToJobResponseDTO);
    }
}
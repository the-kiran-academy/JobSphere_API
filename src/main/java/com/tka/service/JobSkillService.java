package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.JobSkillCreateDTO;
import com.tka.entity.Job;
import com.tka.entity.JobSkill;
import com.tka.entity.Skill;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.JobRepository;
import com.tka.repository.JobSkillRepository;
import com.tka.repository.SkillRepository;

@Service
public class JobSkillService {
    private final JobSkillRepository jobSkillRepository;
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public JobSkillService(JobSkillRepository jobSkillRepository, 
                          JobRepository jobRepository, 
                          SkillRepository skillRepository) {
        this.jobSkillRepository = jobSkillRepository;
        this.jobRepository = jobRepository;
        this.skillRepository = skillRepository;
    }

    public void addSkillToJob(JobSkillCreateDTO dto) {
        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        if (jobSkillRepository.existsByJobIdAndSkillId(dto.getJobId(), dto.getSkillId())) {
            throw new IllegalArgumentException("Skill already associated with job");
        }
        JobSkill jobSkill = new JobSkill();
        jobSkill.setJob(job);
        jobSkill.setSkill(skill);
        jobSkillRepository.save(jobSkill);
    }
}
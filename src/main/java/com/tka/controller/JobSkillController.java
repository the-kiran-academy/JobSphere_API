package com.tka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.JobSkillCreateDTO;
import com.tka.service.JobSkillService;

@RestController
@RequestMapping("/api/job-skills")
public class JobSkillController {
    private final JobSkillService jobSkillService;

    @Autowired
    public JobSkillController(JobSkillService jobSkillService) {
        this.jobSkillService = jobSkillService;
    }

    @PostMapping
    public void addSkillToJob(@Valid @RequestBody JobSkillCreateDTO dto) {
        jobSkillService.addSkillToJob(dto);
    }
}
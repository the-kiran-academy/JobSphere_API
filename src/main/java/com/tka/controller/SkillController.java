package com.tka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.SkillCreateDTO;
import com.tka.dtos.SkillResponseDTO;
import com.tka.service.SkillService;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillResponseDTO createSkill(@Valid @RequestBody SkillCreateDTO dto) {
        return skillService.createSkill(dto);
    }

    @GetMapping("/{id}")
    public SkillResponseDTO getSkill(@PathVariable Long id) {
        return skillService.getSkill(id);
    }

    @GetMapping
    public List<SkillResponseDTO> getAllSkills() {
        return skillService.getAllSkills();
    }
}
package com.tka.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.SkillCreateDTO;
import com.tka.dtos.SkillResponseDTO;
import com.tka.entity.Skill;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.SkillRepository;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillResponseDTO createSkill(SkillCreateDTO dto) {
        if (skillRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Skill already exists");
        }
        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill = skillRepository.save(skill);
        return mapToSkillResponseDTO(skill);
    }

    public SkillResponseDTO getSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        return mapToSkillResponseDTO(skill);
    }

    public List<SkillResponseDTO> getAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(this::mapToSkillResponseDTO)
                .collect(Collectors.toList());
    }

    private SkillResponseDTO mapToSkillResponseDTO(Skill skill) {
        SkillResponseDTO dto = new SkillResponseDTO();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        return dto;
    }
}
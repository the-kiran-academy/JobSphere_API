package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.UserSkillCreateDTO;
import com.tka.entity.Skill;
import com.tka.entity.User;
import com.tka.entity.UserSkill;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.SkillRepository;
import com.tka.repository.UserRepository;
import com.tka.repository.UserSkillRepository;

@Service
public class UserSkillService {
    private final UserSkillRepository userSkillRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public UserSkillService(UserSkillRepository userSkillRepository, 
                           UserRepository userRepository, 
                           SkillRepository skillRepository) {
        this.userSkillRepository = userSkillRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    public void addSkillToUser(UserSkillCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Skill skill = skillRepository.findById(dto.getSkillId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        if (userSkillRepository.existsByUserIdAndSkillId(dto.getUserId(), dto.getSkillId())) {
            throw new IllegalArgumentException("Skill already associated with user");
        }
        UserSkill userSkill = new UserSkill();
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkillRepository.save(userSkill);
    }
}
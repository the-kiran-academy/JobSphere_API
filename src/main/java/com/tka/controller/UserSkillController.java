package com.tka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.UserSkillCreateDTO;
import com.tka.service.UserSkillService;

@RestController
@RequestMapping("/api/user-skills")
public class UserSkillController {
    private final UserSkillService userSkillService;

    @Autowired
    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @PostMapping
    public void addSkillToUser(@Valid @RequestBody UserSkillCreateDTO dto) {
        userSkillService.addSkillToUser(dto);
    }
}
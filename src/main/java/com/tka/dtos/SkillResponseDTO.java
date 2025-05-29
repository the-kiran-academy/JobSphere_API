package com.tka.dtos;

public class SkillResponseDTO {
    private Long id;
    private String name;

    // Default constructor
    public SkillResponseDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
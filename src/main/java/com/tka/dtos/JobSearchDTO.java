package com.tka.dtos;

import java.util.List;

public class JobSearchDTO {
	private String keyword;
	private Long categoryId;
	private Long locationId;
	private List<Long> skillIds;
	private Double minSalary;
	private Double maxSalary;

	// Getters and setters
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public List<Long> getSkillIds() {
		return skillIds;
	}

	public void setSkillIds(List<Long> skillIds) {
		this.skillIds = skillIds;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
}
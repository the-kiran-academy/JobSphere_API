package com.tka.dtos;

public class JobAnalyticsResponseDTO {
    private Long jobId;
    private long views;
    private long applications;

    // Getters and setters
    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }
    public long getViews() { return views; }
    public void setViews(long views) { this.views = views; }
    public long getApplications() { return applications; }
    public void setApplications(long applications) { this.applications = applications; }
}
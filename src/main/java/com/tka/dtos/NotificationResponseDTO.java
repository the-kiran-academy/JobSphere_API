package com.tka.dtos;

import java.time.LocalDateTime;

public class NotificationResponseDTO {
	private Long id;
	private Long userId;
	private String message;
	private LocalDateTime createdAt;
	private boolean isRead;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setIsRead(boolean read) {
		this.isRead = read;
	}
}
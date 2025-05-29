package com.tka.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.NotificationResponseDTO;
import com.tka.entity.Notification;
import com.tka.entity.User;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.NotificationRepository;
import com.tka.repository.UserRepository;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createNotification(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    public List<NotificationResponseDTO> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(Mapper::mapToNotificationResponseDTO)
                .collect(Collectors.toList());
    }
}
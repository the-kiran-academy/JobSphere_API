package com.tka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dtos.UserLoginDTO;
import com.tka.dtos.UserRegisterDTO;
import com.tka.dtos.UserResponseDTO;
import com.tka.entity.User;
import com.tka.enums.Role;
import com.tka.exceptions.ResourceNotFoundException;
import com.tka.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	public UserResponseDTO registerUser(UserRegisterDTO dto) {
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(Role.valueOf(dto.getRole()));
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPhone(dto.getPhone());
		user = userRepository.save(user);
		return mapToUserResponseDTO(user);
	}

	public UserResponseDTO loginUser(UserLoginDTO dto) {
		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		if (dto.getPassword() == null || !user.getPassword().equals(dto.getPassword())) {
			throw new IllegalArgumentException("Invalid credentials");
		}
		return mapToUserResponseDTO(user);
	}

	public UserResponseDTO getUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return mapToUserResponseDTO(user);
	}

	public UserResponseDTO updateUser(Long id, UserRegisterDTO dto) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPhone(dto.getPhone());
		user = userRepository.save(user);
		return mapToUserResponseDTO(user);
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
	}

	private UserResponseDTO mapToUserResponseDTO(User user) {
		UserResponseDTO dto = new UserResponseDTO();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole().name());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setPhone(user.getPhone());
		dto.setCreatedAt(user.getCreatedAt());
		dto.setUpdatedAt(user.getUpdatedAt());
		return dto;
	}

	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToUserResponseDTO).toList();
	}

	public List<UserResponseDTO> getAllEmployers() {
		
		List<User> employers = userRepository.findByRole(Role.EMPLOYER);
		return employers.stream().map(this::mapToUserResponseDTO).toList();
	}

	public List<UserResponseDTO> getAllJobSeekers() {
		List<User> jobSeekers = userRepository.findByRole(Role.JOB_SEEKER);
		return jobSeekers.stream().map(this::mapToUserResponseDTO).toList();
	}
}
package com.tka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.dtos.UserLoginDTO;
import com.tka.dtos.UserRegisterDTO;
import com.tka.dtos.UserResponseDTO;
import com.tka.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRegisterDTO dto) {
        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid @RequestBody UserLoginDTO dto) {
        return userService.loginUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
    
    @GetMapping("/all")
	public List<UserResponseDTO> getAllUsers() {
		return userService.getAllUsers();
	}
    
    
    @GetMapping("/all/employers")
    public List<UserResponseDTO> getAllEmployers(){
    	return userService.getAllEmployers();
    }
    
    @GetMapping("/all/jobseekers")
    public List<UserResponseDTO> getAllJobSeekers(){
    	return userService.getAllJobSeekers();
    }
    

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserRegisterDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
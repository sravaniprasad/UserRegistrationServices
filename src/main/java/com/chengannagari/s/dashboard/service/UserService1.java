package com.chengannagari.s.dashboard.service;

	import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;


	@Service
	public class UserService1 {

	    @Autowired
	    private UserRepository userRepository;

	    // Method to get user details by ID
	    public User getUserById(int userId) {
	        // Assuming userRepository.findById() returns an Optional<User>
	        return userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	    }
	  

	}



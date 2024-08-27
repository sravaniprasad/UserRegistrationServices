package com.chengannagari.s.dashboard.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.JwtAuthenticationResponse;
import com.chengannagari.s.dashboard.Entity.SigninRequest;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Entity.UserDTO;
@Service
public interface UserService {

	//String addUser(UserDTO userDTO);

	String addUser(UserDTO userDTO);

	User getUserByName(String firstName);

	User updateUser(User user, String firstName);

	User getUserByUserName(String userName);

	User getUserByPhoneNumber(long phoneNumber);

	User getUserByEmail(String email);

	Optional<User> findByEmail(String email);

	Optional<User> findByPhoneNumber(long phoneNumber);



	
//	JwtAuthenticationResponse signin(SigninRequest signinRequest);
//
//	UserDetailsService userDetailsService();
//	
//	UserDetailsService userDetailsService();
}

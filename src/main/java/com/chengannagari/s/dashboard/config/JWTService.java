package com.chengannagari.s.dashboard.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.chengannagari.s.dashboard.Entity.User;

public interface JWTService {
	
	String extractUserName(String token);
	String generateToken(UserDetails userDetails);
	 boolean isTokenValid(String token, UserDetails userDetails);
	String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails);
	
	}

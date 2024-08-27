package com.chengannagari.s.dashboard.config;

import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		PrintWriter writer=response.getWriter();
		writer.println("Access denied!!! "+authException.getMessage());
		//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
		 
	}
//	@ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
//    }
}

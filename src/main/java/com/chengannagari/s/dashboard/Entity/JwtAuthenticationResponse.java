package com.chengannagari.s.dashboard.Entity;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtAuthenticationResponse {

	
	  // Constructor with jwtToken argument
    public JwtAuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

	private String jwtToken;
	
	private String userName;
	
	private String refreshToken;
	
	private Role role;
	
	 public JwtAuthenticationResponse role(Role role) {
	        this.role = role;
	        return this;
	    }
	

}

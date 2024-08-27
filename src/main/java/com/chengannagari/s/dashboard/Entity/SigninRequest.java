package com.chengannagari.s.dashboard.Entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SigninRequest {

	private String email;
	 
	private String password;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SigninRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public SigninRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SigninRequest other = (SigninRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
	
	
	
}

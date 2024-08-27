package com.chengannagari.s.dashboard.Entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements UserDetails {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="firstName", length=50,nullable = false)
	@NotNull
	private String firstName;
	
	@Column(name="lastName", length=50,nullable = false)
	@NotNull
	private String lastName;
	
	@Column(name="userName", length=50,nullable = false)
	@NotNull
	private String userName;
	
	@Column(name="gender", length=50,nullable = false)
	@NotNull
	private String gender;
	
//	@Column(name="date of birth",nullable = false)
//	@NotNull
	private Date dateOfBirth;
	
	@Column(name="country", length=50,nullable = false)
	@NotNull
	private String country;
	
	@Column(name="state", length=50,nullable = false)
	@NotNull
	private String state;
	
	@Column(name="district", length=50,nullable = false)
	@NotNull
	private String district;
	
	@Column(name="address", length=50,nullable = false)
	@NotNull
	private String address;
	
	@Column(name="phoneNumber", length=10,nullable = false)
	@NotNull
	private long phoneNumber;
	
	@Column(name="email", length=50, nullable = false)
	@NotNull
	private String email;
	
	@Column(name="password",nullable = false)
	@NotNull
	private String password;
	
	@Column(name="image",nullable = false)
	@NotNull
	public String image;
	
	public Role role;
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private RefreshToken refreshToken;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		  return Collections.singleton(new SimpleGrantedAuthority(role.name()));
		    
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email; 
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	 public void setPassword(String password) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        this.password = passwordEncoder.encode(password);
	    }

	

}

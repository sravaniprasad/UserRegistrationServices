package com.chengannagari.s.dashboard.serviceImplementation;

import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.JwtAuthenticationResponse;
import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.SigninRequest;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Entity.UserDTO;
import com.chengannagari.s.dashboard.Exceptions.ResourceNotFoundException;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
import com.chengannagari.s.dashboard.config.JWTService;
import com.chengannagari.s.dashboard.dropdowns.Country;
import com.chengannagari.s.dashboard.dropdowns.District;
import com.chengannagari.s.dashboard.dropdowns.State;
import com.chengannagari.s.dashboard.dropdownsRepository.CountryRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.DistrictRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.StateRepository;
import com.chengannagari.s.dashboard.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;
    
  

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());    
        user.setLastName(userDTO.getLastName());  
        user.setUserName(userDTO.getUserName());  
        user.setGender(userDTO.getGender());  
        user.setDateOfBirth(userDTO.getDateOfBirth());
        Long countryId = Long.parseLong(userDTO.getCountry()); // Convert String to Long
        Optional<Country> countryOptional = countryRepository.findById(countryId);

        if (countryOptional.isPresent()) {
            Country country = countryOptional.get();
            user.setCountry(country.getName()); // Set country name
        }

        // Fetch state name based on state ID
        Long stateId = Long.parseLong(userDTO.getState()); 
        Optional<State> stateOptional = stateRepository.findById(stateId);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            user.setState(state.getName()); // Set state name
        }

        // Fetch district name based on district ID
        Long districtId = Long.parseLong(userDTO.getDistrict()); 
        Optional<District> districtOptional = districtRepository.findById(districtId);
        if (districtOptional.isPresent()) {
            District district = districtOptional.get();
            user.setDistrict(district.getName()); // Set district name
        }
      
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());  
        user.setEmail(userDTO.getEmail());
        
        user.setPassword(user.getPassword());
        user.setImage(userDTO.getImage());
        user.setRole(Role.USER);               
        
        userRepository.save(user);
        return user.getFirstName() + " is saved";
    }
	@Override
	public User getUserByName(String firstName) {
		// TODO Auto-generated method stub
		Optional <User> u=userRepository.findByFirstName(firstName);
		User user=null;
		if(u.isPresent()) {
			user=u.get();
		}
		return user;
	} 
	
	@Override
	public User updateUser(User user, String firstName) {
		// TODO Auto-generated method stub
		User existingUser=userRepository.findByFirstName(firstName).orElse(user);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setUserName(user.getUsername());
		existingUser.setGender(user.getGender());
		existingUser.setCountry(user.getCountry());
		existingUser.setState(user.getState());
		existingUser.setDistrict(user.getDistrict());
		existingUser.setAddress(user.getAddress());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setImage(user.getImage());
		userRepository.save(existingUser);
		return existingUser;
	} 
	@Override
	public User getUserByUserName(String userName) {
		Optional <User> u=userRepository.findByUserName(userName);
		User user=null;
		if(u.isPresent()) {
			user=u.get();
		}
		return user;
	}
	
	
	 
	@Override
	public User getUserByPhoneNumber(long phoneNumber) {
		Optional <User> u=userRepository.findByPhoneNumber(phoneNumber);
		User user=null;
		if(u.isPresent()) {
			user=u.get();
		}
		return user;
	}
	  @Override
	    public User getUserByEmail(String email) {
	        Optional<User> userOptional = userRepository.findByEmail(email);
	        return userOptional.orElse(null); // Handle case where user is not found
	    }
	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
	@Override
	public Optional<User> findByPhoneNumber(long phoneNumber) {
		// TODO Auto-generated method stub
		return userRepository.findByPhoneNumber(phoneNumber);
	}
	
	
	  
}

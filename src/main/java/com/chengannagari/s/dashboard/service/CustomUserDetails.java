//package com.chengannagari.s.dashboard.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.chengannagari.s.dashboard.Entity.User;
//import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
//
//public class CustomUserDetails implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User user=userRepo.findByEmail(username).orElseThrow(()-> new RuntimeException("User not found!!"));
//		return user;
//	}
//
//}
// 
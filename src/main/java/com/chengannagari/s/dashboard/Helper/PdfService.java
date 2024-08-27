package com.chengannagari.s.dashboard.Helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PdfService {

	@Autowired
	UserRepository userRepo;
	
	public List<User> listAll(){
		return userRepo.findAll();
	}
}

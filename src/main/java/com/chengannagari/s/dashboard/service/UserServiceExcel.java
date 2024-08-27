package com.chengannagari.s.dashboard.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Helper.ExcelHelper;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
@Service
public class UserServiceExcel {

	@Autowired
	UserRepository userRepo;
public void save(MultipartFile file) {
		
		try {
			
			List<User> emp = ExcelHelper.ConvertExcelToListOfEmployee(file.getInputStream());
			this.userRepo.saveAll(emp);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}

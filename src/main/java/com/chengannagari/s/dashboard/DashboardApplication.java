package com.chengannagari.s.dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
import com.chengannagari.s.dashboard.config.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DashboardApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (userRepo.count() == 0) {
            // Insert default data
            User u = new User();
            u.setFirstName("Admin");
    		u.setLastName("Admin");
    		u.setUserName("Admin");
    		u.setGender("female");
    		u.setCountry("India");
    		u.setState("Andhrapradesh");
    		u.setDistrict("kadapa");
    		u.setAddress("santhinagar, rajampet");
    		u.setPhoneNumber(982274848);
    		u.setEmail("Admin@gmail.com");
    		u.setPassword("Adminlogging");
    		u.setImage("c:/path");
    		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		    try {
    		        Date dateOfBirth = dateFormat.parse("24/06/1999");
    		        u.setDateOfBirth(dateOfBirth);
    		    } catch (ParseException e) {
    		        // Handle parsing exception
    		        e.printStackTrace();
    		    }
            u.setRole(Role.ADMIN);
            // Set other properties...

            // Save the user
            userRepo.save(u);
        }
		
	}

}

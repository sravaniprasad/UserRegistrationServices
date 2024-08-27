package com.chengannagari.s.dashboard.password;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Helper.EmailService;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class ForgotPasswordService {
 
	@Autowired
	private UserRepository userRepo;
  
    @Autowired 
    private EmailService emailService;
     
    @Bean  
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
 
    private Map<String, String> otpMap = new HashMap<>();
    
    private Map<Long, Integer> otpMap1 = new ConcurrentHashMap<>();
 
    public void sendResetPasswordOTP(String email) {  
        String otp = generateOTP();
        otpMap.put(email, otp); 
        String message = "Here is your OTP to reset your password " + otp;
        emailService.sendEmail(email, "Reset Password OTP", message);
    }
 
    public void resetPassword(String email, String otp, String newPassword) {
        String storedOTP = otpMap.get(email);
        String Password = passwordEncoder().encode(newPassword);
          if (storedOTP != null && storedOTP.equals(otp)) {
            userRepo.updatePasswordByEmail(email, Password);
            otpMap.remove(email); 
        } else {
            throw new RuntimeException("Invalid OTP");
        }
    }

    private String generateOTP() {
        // Generate and return a random OTP
    	 return UUID.randomUUID().toString().substring(0, 6); 
    }
    @Value("${twilio.account_sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth_token}")
    private String twilioAuthToken;

    @Value("${twilio.trial_number}")
    private String twilioPhoneNumber;

    public boolean sendOTPAndResetPassword(long phoneNumber, int otp) {
        otpMap1.put(phoneNumber, otp); 
       
        Twilio.init(twilioAccountSid, twilioAuthToken);

        
        try { 
            String countryCode = "91";
			Message message = Message.creator(
                    new PhoneNumber("+" + countryCode + phoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    "Your OTP for password reset: " + otp) 
                    .create(); 

            System.out.println("OTP sent to " + phoneNumber + " via Twilio.");
            return true;
        } catch (Exception e) {
            System.err.println("Error sending OTP via Twilio: " + e.getMessage());
            return false;
        }
    }


    public boolean verifyOTP(long phoneNumber, int otp) {
        Integer storedOTP = otpMap1.get(phoneNumber);
        if (storedOTP != null && storedOTP == otp) {
            otpMap1.remove(phoneNumber);
            return true;
        }
        return false;
    }

    public void updatePassword(long phoneNumber, String newPassword) {
        Optional<User> userOptional = userRepo.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepo.save(user);
        }
    }
}

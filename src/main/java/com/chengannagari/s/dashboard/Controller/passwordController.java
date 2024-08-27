package com.chengannagari.s.dashboard.Controller;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
import com.chengannagari.s.dashboard.password.ForgotPasswordRequest;
import com.chengannagari.s.dashboard.password.ForgotPasswordService;
import com.chengannagari.s.dashboard.password.ResetPasswordRequest;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class passwordController {

    @Autowired
    private ForgotPasswordService passwordService;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            Optional<User> optionalUser = userRepo.findByEmail(request.getEmail());
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Email not found"));
            }
            
            passwordService.sendResetPasswordOTP(request.getEmail());
            return ResponseEntity.ok(Map.of("message", "OTP sent to your Registered email"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "An unexpected error occurred"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            passwordService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
            return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(Map.of("error", "Invalid OTP!!! Enter valid OTP to reset your password"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "An unexpected error occurred"));
        }
    }

  
    @PostMapping("/forgot-password-phone")
    public ResponseEntity<?> forgotPasswordByPhoneNumber(@RequestParam long phoneNumber) {
        try {
        	  int otp = generateOTP();
              if (passwordService.sendOTPAndResetPassword(phoneNumber, otp))  {
                return ResponseEntity.ok(Map.of("message", "OTP sent successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body(Map.of("error", "Failed to send OTP or user not found"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "An unexpected error occurred"));
        }
    }

    @PostMapping("/reset-password-phone")
    public ResponseEntity<?> resetPasswordByPhoneNumber(@RequestParam long phoneNumber, @RequestParam int otp, @RequestParam String newPassword) {
        try {
            if (passwordService.verifyOTP(phoneNumber, otp)) {
                passwordService.updatePassword(phoneNumber, newPassword);
                return ResponseEntity.ok(Map.of("message", "Password reset successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body(Map.of("error", "Invalid OTP"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", "An unexpected error occurred"));
        }
    }
    private int generateOTP() {
        
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}

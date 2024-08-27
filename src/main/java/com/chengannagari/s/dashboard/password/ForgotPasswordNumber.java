//package com.chengannagari.s.dashboard.password;
//
//import java.util.Optional;
//
//import com.chengannagari.s.dashboard.Entity.User;
//import com.chengannagari.s.dashboard.Entity.UserDTO;
//import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
//
//public class ForgotPasswordNumber {
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private SMSService smsService; // Assume you have a service to send SMS
//
//    public void sendOTPForPasswordReset(String phoneNumber) {
//        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            String otp = OTPUtil.generateOTP(6); // Generate a 6-digit OTP
//            smsService.sendOTP(phoneNumber, otp); // Send OTP via SMS
//            // Save the OTP in the database or in a cache for verification
//            user.setOtp(otp); // Assuming you have a setOtp method in your User class
//            userRepository.save(user);
//        } else {
//            // User not found for the given phone number
//            // Handle this case accordingly
//        }
//    }
//
//    public void resetPasswordWithOTP(String phoneNumber, String otp, String newPassword) {
//        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            // Verify OTP
//            if (user.getOtp() != null && user.getOtp().equals(otp)) {
//                // Reset password
//                user.setPassword(newPassword); // Assuming you have a setPassword method in your User class
//                user.setOtp(null); // Clear OTP after successful password reset
//                userRepository.save(user);
//                // Password reset successful
//            } else {
//                // OTP verification failed
//                // Handle the case where OTP verification fails
//            }
//        } else {
//            // User not found for the given phone number
//            // Handle this case accordingly
//        }
//    }
//}



package com.chengannagari.s.dashboard.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chengannagari.s.dashboard.Entity.JwtAuthenticationResponse;
import com.chengannagari.s.dashboard.Entity.RefreshToken;
import com.chengannagari.s.dashboard.Entity.RefreshTokenRequest;
import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.Entity.SigninRequest;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.config.JwtHelper;
import com.chengannagari.s.dashboard.serviceImplementation.RefreshTokenService;


@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

 @Autowired
 private UserDetailsService userDetailsService;

 @Autowired
 private AuthenticationManager manager;

 @Autowired
 private RefreshTokenService refreshTokenService;
 
 @Autowired
 private JwtHelper helper;

 private Logger logger = LoggerFactory.getLogger(AuthController.class);


 @PostMapping("/login")
 public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SigninRequest request) {

     this.doAuthenticate(request.getEmail(), request.getPassword());

     UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
     String token = this.helper.generateToken(userDetails);

     User user = (User) userDetails;
     Role role = user.getRole();

     RefreshToken refreshToken = refreshTokenService.create(userDetails.getUsername());
     JwtAuthenticationResponse response = JwtAuthenticationResponse.builder()
             .jwtToken(token)
             .refreshToken(refreshToken.getRefreshToken())
             .userName(userDetails.getUsername())
             .role(role) // Include the role in the response
             .build();
     return new ResponseEntity<>(response, HttpStatus.OK);
 }

 private void doAuthenticate(String username, String password) {

     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
     try {
         manager.authenticate(authentication);
     } catch (BadCredentialsException e) {
         throw new BadCredentialsException("Invalid Username or Password");
     }
 }

 @ExceptionHandler(BadCredentialsException.class)
 public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
 }

 @PostMapping("/refresh")
 public JwtAuthenticationResponse refreshJwtToken(@RequestBody RefreshTokenRequest request) throws Exception {
     RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
     User user = refreshToken.getUser();
     String token = this.helper.generateToken(user);
     return JwtAuthenticationResponse.builder()
             .refreshToken(refreshToken.getRefreshToken())
             .jwtToken(token)
             .userName(user.getUsername())
             .role(user.getRole()) 
             .build();
 }
}

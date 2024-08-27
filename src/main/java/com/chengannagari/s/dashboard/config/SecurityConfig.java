package com.chengannagari.s.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chengannagari.s.dashboard.Entity.Role;
import com.chengannagari.s.dashboard.service.UserService;

import lombok.RequiredArgsConstructor;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JWTAuthenticationFilter filter;

   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	 http.csrf().disable()
         .cors().and()
                .authorizeRequests().
                requestMatchers("/test","api/user/all",
                		
                		"api/user/name/{firstName}",
                		"api/user/username/{userName}",
                		
                		
                		"api/user/signin").authenticated()
                .requestMatchers("/auth/login").permitAll().requestMatchers("api/user/delete/{firstName}","api/user/data/{firstName}","/api/images/upload","/api/images/{fileName}","api/user/save","api/user/update/{firstName}","api/user/mail/{email}","api/user/data/upload","api/user/allusers","api/user/download_userspdf","api/user/phonenumber/{phoneNumber}","api/user/getCountry","api/user/getStateByCountryId","api/user/getDistrictByStateId")
                .permitAll()
                .requestMatchers("api/forgot-password","api/forgot-password-phone").permitAll()
                .requestMatchers("api/reset-password","api/reset-password-phone").permitAll()
               
                .requestMatchers("/auth/refresh").permitAll()
                .anyRequest() 
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Allow requests from any origin
        config.addAllowedMethod("*"); // Allow all HTTP methods
        config.addAllowedHeader("*"); // Allow all headers
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
            return builder.getAuthenticationManager();
        }
  
      

    }
	


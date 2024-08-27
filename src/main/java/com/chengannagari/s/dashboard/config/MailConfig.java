package com.chengannagari.s.dashboard.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
public class MailConfig {

	  @Bean 
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587); 
	        mailSender.setUsername("sravaniprasad939@gmail.com");
	        mailSender.setPassword("ozag uzci tnju dvph");

	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        mailSender.setJavaMailProperties(properties);

	        return mailSender;
	    }
}
 
package com.chengannagari.s.dashboard.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//import org.springframework.stereotype.Service;
//import java.util.Properties;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
@Service
public class EmailService {
	 
	  @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        javaMailSender.send(message);
	    }
}
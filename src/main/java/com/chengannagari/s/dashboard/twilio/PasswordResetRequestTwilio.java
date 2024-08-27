package com.chengannagari.s.dashboard.twilio;

import lombok.Data;

@Data
public class PasswordResetRequestTwilio {

	  private String phoneNumber;//destination
	    private String userName;
	    private String oneTimePassword;
}

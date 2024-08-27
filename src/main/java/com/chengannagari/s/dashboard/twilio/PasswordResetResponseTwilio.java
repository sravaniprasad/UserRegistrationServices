package com.chengannagari.s.dashboard.twilio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetResponseTwilio {

    private OtpStatus status;
    private String message;
}

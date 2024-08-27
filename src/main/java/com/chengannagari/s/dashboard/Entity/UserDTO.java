package com.chengannagari.s.dashboard.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    public Date dateOfBirth;
    private String country;
    private String state;
    private String district;
    private String address;
    private long phoneNumber;
    private String email;
    private String password;
    public String image;

 }

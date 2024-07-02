package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;


@Data
@AllArgsConstructor
public class UserDTO {
    String userId;
    String sessionId;
    String firstName;
    String lastName;
    String dob;
    String sex;
    String country;
    String city;
    String state;
    String street;
    String zip;
    String phone;
    String email;
    double balance;
    String balanceCy;
    boolean activated;
    String userCat;
}


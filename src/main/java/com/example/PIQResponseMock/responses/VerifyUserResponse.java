package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyUserResponse {
    String userId;
    boolean success;
    String userCat;
    String kycStatus;
    String sex;
    String firstName;
    String lastName;
    String street;
    String city;
    String state;
    String zip;
    String country;
    String email;
    String dob;
    String mobile;
    double balance;
    String balanceCy;
    String locale;
    Attributes attributes;
    String allow_manual_payout;
    int errCode;
    String errMsg;
}



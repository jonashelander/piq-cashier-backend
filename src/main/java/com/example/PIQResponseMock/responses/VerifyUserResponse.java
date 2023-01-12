package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUserResponse {
    public VerifyUserResponse(String userId, boolean success, String kycStatus) {
        this.userId = userId;
        this.success = success;
        this.kycStatus = kycStatus;
    }

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



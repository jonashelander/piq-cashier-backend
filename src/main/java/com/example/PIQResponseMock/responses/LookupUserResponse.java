package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LookupUserResponse {
    String userId;
    boolean success;
    String userCat;
    String kycStatus;
    String sex;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    String country;
    String email;
    String dob;
    String mobile;
    double balance;
    String balanceCy;
    String locale;
    Attributes attributes;
    int errCode;
    String errMsg;

}

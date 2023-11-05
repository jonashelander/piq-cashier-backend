package com.example.PIQResponseMock.responses;

import lombok.Data;

@Data
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
    int errCode = 0;
    String errMsg = "No errors this time!";

    public VerifyUserResponse(String userId, boolean success, String kycStatus) {
        this.userId = userId;
        this.success = success;
        this.kycStatus = kycStatus;
    }

    public VerifyUserResponse(String userId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, Attributes attributes, int errCode, String errMsg) {
        this.userId = userId;
        this.success = success;
        this.userCat = userCat;
        this.kycStatus = kycStatus;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.balance = balance;
        this.balanceCy = balanceCy;
        this.locale = locale;
        this.attributes = attributes;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public VerifyUserResponse(String userId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, Attributes attributes) {
        this.userId = userId;
        this.success = success;
        this.userCat = userCat;
        this.kycStatus = kycStatus;
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.balance = balance;
        this.balanceCy = balanceCy;
        this.locale = locale;
        this.attributes = attributes;
    }
}



package com.example.PIQResponseMock.responses;

import lombok.Data;

@Data
public class VerifyUserResponse {

    String userId;
    String sessionId;
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
    String errCode;
    String errMsg;
    java.util.Map<String, String> headers;

    public VerifyUserResponse(String userId, boolean success, String kycStatus) {
        this.userId = userId;
        this.success = success;
        this.kycStatus = kycStatus;
    }

    public VerifyUserResponse(String userId, String sessionId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, Attributes attributes, String errCode, String errMsg) {
        this.userId = userId;
        this.sessionId = sessionId;
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

    public VerifyUserResponse(String userId, String sessionId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, String errCode, String errMsg, Attributes attributes) {
        this.userId = userId;
        this.sessionId = sessionId;
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
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.attributes = attributes;
    }

    public VerifyUserResponse(String userId, String sessionId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, String errCode, String errMsg, java.util.Map<String, String> headers) {
        this.userId = userId;
        this.sessionId = sessionId;
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
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.headers = headers;
    }

    public VerifyUserResponse(String userId, String sessionId, boolean success, String userCat, String kycStatus, String sex, String firstName, String lastName, String street, String city, String state, String zip, String country, String email, String dob, String mobile, double balance, String balanceCy, String locale, String errCode, String errMsg, Attributes attributes, java.util.Map<String, String> headers) {
        this.userId = userId;
        this.sessionId = sessionId;
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
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.attributes = attributes;
        this.headers = headers;
    }
}

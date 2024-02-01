package com.example.PIQResponseMock.models;

import lombok.Data;

import java.util.UUID;


@Data
public class User {
    String userId;
    String sessionId;
    String userCat;
    String kycStatus = "Verified";
    double balance = 0;
    String balanceCy = "EUR";
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
    String password;
    boolean activated;

    public User(String firstName, String lastName, String dob, String sex, String country, String city, String state, String street, String zip, String phone, String email, String password, boolean activated, String userCat) {
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.activated = true;
        this.userCat = userCat;
    }
}

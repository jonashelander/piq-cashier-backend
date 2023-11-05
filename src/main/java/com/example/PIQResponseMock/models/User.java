package com.example.PIQResponseMock.models;

import lombok.Data;


@Data
public class User {
    String userId;
    String sessionId;
    String userCat = "VIP";
    String kycStatus = "Verified";
    int balance = 200;
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
    boolean isBlocked = false;

    public User(String firstName, String lastName, String dob, String sex, String country, String city, String state, String street, String zip, String phone, String email, String password) {
        this.userId = "JonasEUR";
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
    }
}

package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpResponseDTO {
    String userId;
    String firstName;
    String lastName;
    String dob;
    String sex;
    String country;
    String city;
    String state;
    String street;
    String zip;
    String mobile;
    String email;
    String password;
}

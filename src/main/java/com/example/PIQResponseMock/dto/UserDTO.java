package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO {
    String id;
    String userId;
    String sessionId;
    String token;
    boolean success;
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
    double balance;
    String balanceCy;
    boolean activated;
    String userCat;
    String kycStatus;
    String errCode;
    String errMsg;
    String locale;
    private AuthorizeDTO authorize;
    private TransferDTO transfer;
    private CancelDTO cancel;
}


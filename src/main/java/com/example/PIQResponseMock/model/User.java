package com.example.PIQResponseMock.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    String id;
    String userId;
    String sessionId;
    String token;
    boolean success = true;
    String test;
    String userCat;
    String kycStatus = "Verified";
    double balance = 10;
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
    String mobile;
    String email;
    String password;
    boolean activated;
    String errCode;
    String errMsg;
    String locale;
    String authCode;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Authorize authorize;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Transfer transfer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Cancel cancel;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private VerifyUserLog verifyUserLog;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private AuthorizeLog authorizeLog;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private TransferLog transferLog;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private CancelLog cancelLog;




    public User(String userId, String firstName, String lastName, String dob, String sex, String country, String city, String state, String street, String zip, String mobile, String email, String password, boolean activated, String userCat, String errCode, String errMsg, String locale, String authCode) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.activated = true;
        this.userCat = userCat;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.locale = locale;
        this.authCode = authCode;
    }
}

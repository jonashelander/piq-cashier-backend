package com.example.PIQResponseMock.responses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.swing.text.View;

@Data
public class AuthorizeResponse {

    String userId;
    boolean success;
    String authCode;
    int errCode = 0;
    String errMsg = "No errors this time!";


    public AuthorizeResponse(String userId, boolean success, String authCode) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
    }

    public AuthorizeResponse(String userId, boolean success, String authCode, int errCode, String errMsg) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

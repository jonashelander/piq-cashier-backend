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
    String errCode;
    String errMsg;


    public AuthorizeResponse(String userId, boolean success, String authCode) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
    }

    public AuthorizeResponse(String userId, boolean success, String authCode, String errCode, String errMsg) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

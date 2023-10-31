package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthorizeResponse {

    String userId;
    boolean success;
    String authCode;
    int errCode;
    String errMsg;


    public AuthorizeResponse(String userId, boolean success, String authCode, int errCode, String errMsg) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public AuthorizeResponse(String userId, boolean success, String authCode) {
        this.userId = userId;
        this.success = success;
        this.authCode = authCode;
    }
}

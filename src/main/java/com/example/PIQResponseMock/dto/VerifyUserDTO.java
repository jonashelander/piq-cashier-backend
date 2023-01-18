package com.example.PIQResponseMock.dto;


import lombok.Data;


public class VerifyUserDTO {
    String userId;
    String sessionId;

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }
}

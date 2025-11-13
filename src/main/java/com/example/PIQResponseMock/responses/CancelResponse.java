package com.example.PIQResponseMock.responses;

import lombok.Data;

import java.util.Map;

@Data
public class CancelResponse {
    private String userId;
    boolean success;
    String errCode;
    String errMsg;
    Map<String, String> headers;

    public CancelResponse(String userId, boolean success, String errCode, String errMsg, Map<String, String> headers) {
        this.userId = userId;
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.headers = headers;
    }
}
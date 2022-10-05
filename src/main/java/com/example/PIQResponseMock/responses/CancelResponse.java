package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelResponse {
    private String userId;
    boolean success;
    int errCode;
    String errMsg;
}
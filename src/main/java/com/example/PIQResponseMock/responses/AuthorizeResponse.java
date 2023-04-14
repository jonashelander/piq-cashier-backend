package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizeResponse {

        String userId;
        boolean success;
        int merchantTxId;
        String authCode;
        int errCode;
        String errMsg;
//        String updatedUser;
}

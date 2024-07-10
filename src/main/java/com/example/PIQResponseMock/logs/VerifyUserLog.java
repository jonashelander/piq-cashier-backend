package com.example.PIQResponseMock.logs;

import java.time.LocalDateTime;

public class VerifyUserLog {
    String sessionId;
    String userId;LocalDateTime time = LocalDateTime.now();


    public VerifyUserLog(String sessionId, String userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }
}

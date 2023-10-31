package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthDTO {
    String userId;
    String sessionId;
}

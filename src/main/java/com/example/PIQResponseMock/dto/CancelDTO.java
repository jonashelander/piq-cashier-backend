package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelDTO {
    String userId;
    boolean success;
    String errCode;
    String errMsg;
}

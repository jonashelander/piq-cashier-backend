package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;

@Data
@AllArgsConstructor
public class AuthorizeDTO {
    String userId;
    boolean success;
    String merchantTxId;
    String authCode;
    String errCode;
    String errMsg;
}

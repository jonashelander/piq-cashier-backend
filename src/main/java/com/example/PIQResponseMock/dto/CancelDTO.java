package com.example.PIQResponseMock.dto;

import com.example.PIQResponseMock.responses.Attributes;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelDTO {
    String userId;
    String authCode;
    String txAmount;
    String txAmountCy;
    String txId;
    String txTypeId;
    String txName;
    String provider;

}

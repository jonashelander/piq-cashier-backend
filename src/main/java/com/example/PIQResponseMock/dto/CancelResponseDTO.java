package com.example.PIQResponseMock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelResponseDTO {
    String userId;
    String authCode;
    String txAmount;
    String txAmountCy;
    String txId;
    String txTypeId;
    String txName;
    String provider;
}

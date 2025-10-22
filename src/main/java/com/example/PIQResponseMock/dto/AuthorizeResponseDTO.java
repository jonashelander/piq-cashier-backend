package com.example.PIQResponseMock.dto;

import com.example.PIQResponseMock.responses.Attributes;
import lombok.Data;

@Data
public class AuthorizeResponseDTO {
    String userId;
    String txAmount;
    String txAmountCy;
    String txId;
    String txTypeId;
    String txName;
    String provider;
    String pspService;
    String originTxId;
    String accountId;
    String accountHolder;
    String pspFee;
    String pspFeeCy;
    String pspFeeBase;
    String pspFeeBaseCy;
    Attributes attributes;
}

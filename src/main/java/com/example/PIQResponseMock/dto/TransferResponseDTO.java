package com.example.PIQResponseMock.dto;

import com.example.PIQResponseMock.responses.Attributes;
import lombok.Data;

@Data
public class TransferResponseDTO {
    String userId;
    String authCode;
    String txAmount;
    String txAmountCy;
    String txPspAmount;
    String txPspAmountCy;
    String fee;
    String feeCy;
    String feeMode;
    String txId;
    String txTypeId;
    String txName;
    String provider;
    String pspService;
    String txRefId;
    String OriginTxId;
    String accountId;
    String accountHolder;
    String maskedAccount;
    String pspFeeCy;
    String pspFeeBase;
    String pspFeeBaseCy;
    String pspRefId;
    String pspStatusMessage;
    String expiryYear;
    String expiryMonth;
    //Attributes attributes;
}

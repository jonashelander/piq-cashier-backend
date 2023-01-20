package com.example.PIQResponseMock.dto;

import com.example.PIQResponseMock.responses.Attributes;
import lombok.Data;

@Data
public class AuthorizeDTO {
    String userId;
    String txAmount;
    String txAmountCy;
    String txTypeId;
    String txName;
    String Provider;
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

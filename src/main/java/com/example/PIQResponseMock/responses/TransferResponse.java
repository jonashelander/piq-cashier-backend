package com.example.PIQResponseMock.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferResponse {
    String userId;
    boolean success;
    String txId;
    //int merchantTxId;
    //int errCode;
    //String errMsg;
}

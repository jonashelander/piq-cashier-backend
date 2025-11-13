package com.example.PIQResponseMock.responses;

import lombok.Data;

import java.util.Map;

@Data
public class TransferResponse {
    String userId;
    boolean success;
    String txId;
    String merchantTxId;
    String errCode;
    String errMsg;
    Map<String, String> headers;

    public TransferResponse(String userId, boolean success, String txId, String merchantTxId, String errCode, String errMsg, Map<String, String> headers) {
        this.userId = userId;
        this.success = success;
        this.txId = txId;
        this.merchantTxId = merchantTxId;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.headers = headers;
    }
}

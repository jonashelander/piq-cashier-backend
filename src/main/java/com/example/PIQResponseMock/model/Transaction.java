package com.example.PIQResponseMock.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    String authCode;
    String merchantTxId;
    String userId;
    String txName;
    double txAmount;
    boolean finalized = false;

    public Transaction(String userId, String txName, double txAmount) {
        this.authCode = UUID.randomUUID().toString();
        this.merchantTxId = UUID.randomUUID().toString();
        this.userId = userId;
        this.txName = txName;
        this.txAmount = txAmount;
    }
}

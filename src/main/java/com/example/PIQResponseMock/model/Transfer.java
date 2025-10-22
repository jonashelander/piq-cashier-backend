package com.example.PIQResponseMock.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Transfer {
    @Id
    String id;
    String userId;
    boolean success;
    String txId;
    String merchantTxId;
    String errCode;
    String errMsg;

    @OneToOne
    @JoinColumn(name = "user_internal_id") // stable link to User
    @JsonBackReference
    private User user;


    public Transfer(String id, String userId, boolean success, String txId, String merchantTxId, String errCode, String errMsg) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.success = success;
        this.txId = txId;
        this.merchantTxId = merchantTxId;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

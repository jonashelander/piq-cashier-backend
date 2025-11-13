package com.example.PIQResponseMock.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "verify_user_log")
@Data
@NoArgsConstructor
public class VerifyUserLog {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT")
    private String incomingData;

    @Column(columnDefinition = "TEXT")
    private String outgoingData;

    @OneToOne
    @JoinColumn(name = "user_internal_id")
    private User user;

    public VerifyUserLog(String incomingData, String outgoingData) {
        this.id = UUID.randomUUID().toString();
        this.incomingData = incomingData;
        this.outgoingData = outgoingData;
    }
}

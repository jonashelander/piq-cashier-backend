package com.example.PIQResponseMock.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cancel_log")
@Data
@NoArgsConstructor
public class CancelLog {
    @Id
    private String id;

    @Column(columnDefinition = "TEXT")
    private String incomingData;

    @Column(columnDefinition = "TEXT")
    private String outgoingData;

    @OneToOne
    @JoinColumn(name = "user_internal_id")
    @JsonBackReference
    private User user;

    public CancelLog(String incomingData, String outgoingData) {
        this.id = UUID.randomUUID().toString();
        this.incomingData = incomingData;
        this.outgoingData = outgoingData;
    }
}

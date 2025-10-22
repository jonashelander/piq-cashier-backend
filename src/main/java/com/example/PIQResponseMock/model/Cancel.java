package com.example.PIQResponseMock.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Cancel {
    @Id
    String id;
    String userId;
    boolean success;
    String errCode;
    String errMsg;

    @OneToOne
    @JoinColumn(name = "user_internal_id") // stable link to User
    @JsonBackReference
    private User user;

    public Cancel(String id, String userId, boolean success, String errCode, String errMsg) {
        this.id = id;
        this.userId = userId;
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}

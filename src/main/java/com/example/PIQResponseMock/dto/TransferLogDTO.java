package com.example.PIQResponseMock.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class TransferLogDTO {
    private JsonNode incomingData;
    private JsonNode outgoingData;

    public TransferLogDTO(JsonNode incomingData, JsonNode outgoingData) {
        this.incomingData = incomingData;
        this.outgoingData = outgoingData;
    }
}

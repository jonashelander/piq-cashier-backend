package com.example.PIQResponseMock.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyUserLogDTO {
    private JsonNode incomingData;
    private JsonNode outgoingData;
}


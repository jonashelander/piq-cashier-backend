package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.responses.*;
import com.example.PIQResponseMock.services.IntegrationApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/paymentiq", produces = "application/json;charset=utf8")
public class IntegrationApiController {

    IntegrationApiService integrationApiService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public IntegrationApiController(IntegrationApiService integrationApiService) {
        this.integrationApiService = integrationApiService;
    }

    @PostMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyUser(@RequestBody JsonNode rawRequest, HttpServletRequest request) {
        // Collect all incoming headers into a map
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        java.util.Map<String, String> headersMap = new java.util.HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }
        com.example.PIQResponseMock.model.Headers headers = new com.example.PIQResponseMock.model.Headers(headersMap);
        String userId = rawRequest.has("userId") ? rawRequest.get("userId").asText() : null;
        return integrationApiService.verifyUser(userId, rawRequest, headers);
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResponse> authorize(@RequestBody JsonNode rawRequest, HttpServletRequest request) {
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        java.util.Map<String, String> headersMap = new java.util.HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }
        com.example.PIQResponseMock.model.Headers headers = new com.example.PIQResponseMock.model.Headers(headersMap);
        String userId = rawRequest.has("userId") ? rawRequest.get("userId").asText() : null;
        return integrationApiService.authorize(userId, rawRequest, headers);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody JsonNode rawRequest, HttpServletRequest request) {
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        java.util.Map<String, String> headersMap = new java.util.HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }
        com.example.PIQResponseMock.model.Headers headers = new com.example.PIQResponseMock.model.Headers(headersMap);
        String userId = rawRequest.has("userId") ? rawRequest.get("userId").asText() : null;
        return integrationApiService.transfer(userId, rawRequest, headers);
    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelResponse> cancel(@RequestBody JsonNode rawRequest, HttpServletRequest request) {
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        java.util.Map<String, String> headersMap = new java.util.HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }
        com.example.PIQResponseMock.model.Headers headers = new com.example.PIQResponseMock.model.Headers(headersMap);
        String userId = rawRequest.has("userId") ? rawRequest.get("userId").asText() : null;
        return integrationApiService.cancel(userId, rawRequest, headers);
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationResponse> notification(NotificationResponseDTO notificationResponseDTO) {
        return integrationApiService.notification(notificationResponseDTO);
    }

    @PostMapping("/lookupuser")
    public ResponseEntity<LookupUserResponse> lookupuser(LookupUserResponseDTO lookupUserResponseDTO) {
        return integrationApiService.lookupUser(lookupUserResponseDTO);
    }

}

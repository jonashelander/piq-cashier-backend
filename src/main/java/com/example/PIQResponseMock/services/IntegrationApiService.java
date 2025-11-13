package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.helpers.Convert;
import com.example.PIQResponseMock.model.*;
import com.example.PIQResponseMock.repositories.AuthorizeRepository;
import com.example.PIQResponseMock.repositories.TransactionRepository;
import com.example.PIQResponseMock.repositories.TransferRepository;
import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.responses.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.example.PIQResponseMock.helpers.LogJsonHelper;

@Service
public class IntegrationApiService {
    UserRepository userRepository;
    AuthService authService;
    TransactionService transactionService;
    TransactionRepository transactionRepository;
    AuthorizeRepository authorizeRepository;

    @Autowired
    public IntegrationApiService(UserRepository userRepository, AuthService authService, TransactionService transactionService, TransactionRepository transactionRepository, AuthorizeRepository authorizeRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
        this.authorizeRepository = authorizeRepository;
    }

    public ResponseEntity<VerifyUserResponse> verifyUser(String userId, JsonNode rawRequest, com.example.PIQResponseMock.model.Headers headers) {
        // Fetch user
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Build response using user entity only (no DTO mapping)
        VerifyUserResponse verifyUserResponse = new VerifyUserResponse(
                user.getUserId(),
                user.getSessionId(),
                user.isSuccess(),
                user.getUserCat(),
                user.getKycStatus(),
                user.getSex(),
                user.getFirstName(),
                user.getLastName(),
                user.getStreet(),
                user.getCity(),
                user.getState(),
                user.getZip(),
                user.getCountry(),
                user.getEmail(),
                user.getDob(),
                user.getMobile(),
                user.getBalance(),
                user.getBalanceCy(),
                user.getLocale(),
                user.getErrCode(),
                user.getErrMsg(),
                new Attributes("something1", "something2"),
                headers.getHeaders()
        );

        try {
            // Log incoming as-is (with logTimeIncoming)
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode incomingNodeWithTime = LogJsonHelper.addLogTime(rawRequest, "logTimeIncoming");
            // Merge headers into the incoming JSON as a top-level 'headers' object
            com.fasterxml.jackson.databind.node.ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.set("headers", objectMapper.valueToTree(headers.getHeaders()));
            incomingNodeWithTime.fields().forEachRemaining(entry -> mergedNode.set(entry.getKey(), entry.getValue()));
            String combinedIncomingJson = objectMapper.writeValueAsString(mergedNode);
            // Outgoing
            ObjectMapper outgoingMapper = new ObjectMapper();
            JsonNode outgoingNode = outgoingMapper.valueToTree(verifyUserResponse);
            JsonNode outgoingNodeWithTime = LogJsonHelper.addLogTime(outgoingNode, "logTimeOutgoing");
            String outgoingJson = outgoingMapper.writeValueAsString(outgoingNodeWithTime);

            // Save to VerifyUserLog
            VerifyUserLog log = user.getVerifyUserLog();
            if (log == null) {
                log = new VerifyUserLog(combinedIncomingJson, outgoingJson);
                log.setUser(user);
                user.setVerifyUserLog(log);
            } else {
                log.setIncomingData(combinedIncomingJson);
                log.setOutgoingData(outgoingJson);
            }

            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log verifyUser data", e);
        }

        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }


    public ResponseEntity<AuthorizeResponse> authorize(String userId, JsonNode rawRequest, com.example.PIQResponseMock.model.Headers headers) {
        // Fetch user
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Build response
        AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                user.getAuthorize().getUserId(),
                user.getAuthorize().isSuccess(),
                user.getAuthorize().getAuthCode(),
                user.getAuthorize().getErrCode(),
                user.getAuthorize().getErrMsg(),
                headers.getHeaders()
        );

        // Convert incoming and outgoing to JSON strings, excluding nulls, with separate log times
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        try {
            JsonNode incomingNodeWithTime = LogJsonHelper.addLogTime(rawRequest, "logTimeIncoming");
            // Merge headers into the incoming JSON as a top-level 'headers' object
            com.fasterxml.jackson.databind.node.ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.set("headers", objectMapper.valueToTree(headers.getHeaders()));
            incomingNodeWithTime.fields().forEachRemaining(entry -> mergedNode.set(entry.getKey(), entry.getValue()));
            String incomingJson = objectMapper.writeValueAsString(mergedNode);

            // Convert AuthorizeResponse to JsonNode and add logTimeOutgoing
            ObjectMapper outgoingMapper = new ObjectMapper();
            JsonNode outgoingNode = outgoingMapper.valueToTree(authorizeResponse);
            JsonNode outgoingNodeWithTime = LogJsonHelper.addLogTime(outgoingNode, "logTimeOutgoing");
            String outgoingJson = outgoingMapper.writeValueAsString(outgoingNodeWithTime);

            // Save to AuthorizeLog
            AuthorizeLog log = user.getAuthorizeLog();
            if (log == null) {
                log = new AuthorizeLog(incomingJson, outgoingJson);
                log.setUser(user);
                user.setAuthorizeLog(log);
            } else {
                log.setIncomingData(incomingJson);
                log.setOutgoingData(outgoingJson);
            }

            userRepository.save(user);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log authorize data", e);
        }

        return new ResponseEntity<>(authorizeResponse, HttpStatus.OK);
    }

    public ResponseEntity<TransferResponse> transfer(String userId, JsonNode rawRequest, com.example.PIQResponseMock.model.Headers headers) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        TransferResponse transferResponse = new TransferResponse(
                user.getTransfer().getUserId(),
                user.getTransfer().isSuccess(),
                user.getTransfer().getTxId(),
                user.getTransfer().getMerchantTxId(),
                user.getTransfer().getErrCode(),
                user.getTransfer().getErrMsg(),
                headers.getHeaders()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        try {
            // Incoming
            JsonNode incomingNodeWithTime = LogJsonHelper.addLogTime(rawRequest, "logTimeIncoming");
            com.fasterxml.jackson.databind.node.ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.set("headers", objectMapper.valueToTree(headers.getHeaders()));
            incomingNodeWithTime.fields().forEachRemaining(entry -> mergedNode.set(entry.getKey(), entry.getValue()));
            String incomingJson = objectMapper.writeValueAsString(mergedNode);
            // Outgoing
            ObjectMapper outgoingMapper = new ObjectMapper();
            JsonNode outgoingNode = outgoingMapper.valueToTree(transferResponse);
            JsonNode outgoingNodeWithTime = LogJsonHelper.addLogTime(outgoingNode, "logTimeOutgoing");
            String outgoingJson = outgoingMapper.writeValueAsString(outgoingNodeWithTime);

            TransferLog log = user.getTransferLog();
            if (log == null) {
                log = new TransferLog(incomingJson, outgoingJson);
                log.setUser(user);
                user.setTransferLog(log);
            } else {
                log.setIncomingData(incomingJson);
                log.setOutgoingData(outgoingJson);
            }

            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log transfer data", e);
        }

        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

    public ResponseEntity<CancelResponse> cancel(String userId, JsonNode rawRequest, com.example.PIQResponseMock.model.Headers headers) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        CancelResponse cancelResponse = new CancelResponse(
                user.getCancel().getUserId(),
                user.getCancel().isSuccess(),
                user.getCancel().getErrCode(),
                user.getCancel().getErrMsg(),
                headers.getHeaders()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        try {
            // Incoming
            JsonNode incomingNodeWithTime = LogJsonHelper.addLogTime(rawRequest, "logTimeIncoming");
            com.fasterxml.jackson.databind.node.ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.set("headers", objectMapper.valueToTree(headers.getHeaders()));
            incomingNodeWithTime.fields().forEachRemaining(entry -> mergedNode.set(entry.getKey(), entry.getValue()));
            String incomingJson = objectMapper.writeValueAsString(mergedNode);
            // Outgoing
            ObjectMapper outgoingMapper = new ObjectMapper();
            JsonNode outgoingNode = outgoingMapper.valueToTree(cancelResponse);
            JsonNode outgoingNodeWithTime = LogJsonHelper.addLogTime(outgoingNode, "logTimeOutgoing");
            String outgoingJson = outgoingMapper.writeValueAsString(outgoingNodeWithTime);

            CancelLog log = user.getCancelLog();
            if (log == null) {
                log = new CancelLog(incomingJson, outgoingJson);
                log.setUser(user);
                user.setCancelLog(log);
            } else {
                log.setIncomingData(incomingJson);
                log.setOutgoingData(outgoingJson);
            }

            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log cancel data", e);
        }

        return new ResponseEntity<>(cancelResponse, HttpStatus.OK);
    }

    public ResponseEntity<NotificationResponse> notification(NotificationResponseDTO notificationResponseDTO) {
        NotificationResponse notificationResponse = new NotificationResponse(
                true,
                400,
                "Something went wrong"
        );
        return new ResponseEntity(notificationResponse, HttpStatus.OK);
    }

    public ResponseEntity<LookupUserResponse> lookupUser(LookupUserResponseDTO lookupUserResponseDTO) {
        LookupUserResponse lookupUserResponse = new LookupUserResponse(
                "JonasEUR",
                true,
                "VIP",
                "Approved",
                "MALE",
                "Jonas",
                "Helander",
                "Praktejdervägen 13",
                "Stockholm",
                "184 61",
                "SWE",
                "helanderjonas@gmail.com",
                "1987-06-29",
                "+46709660528",
                100.5,
                "EUR",
                "sv_SE",
                new Attributes(
                        "attribute one",
                        "attribute two"
                ),
                400,
                "Something went wrong"
        );
        return new ResponseEntity(lookupUserResponse, HttpStatus.OK);
    }
}

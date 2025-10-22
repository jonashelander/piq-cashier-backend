package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.responses.*;
import com.example.PIQResponseMock.services.IntegrationApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/paymentiq", produces = "application/json;charset=utf8")
public class IntegrationApiController {

    IntegrationApiService integrationApiService;

    @Autowired
    public IntegrationApiController(IntegrationApiService integrationApiService) {
        this.integrationApiService = integrationApiService;
    }

    @PostMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyUser(@RequestBody VerifyUserResponseDTO verifyUserResponseDTO) {
        return integrationApiService.verifyUser(verifyUserResponseDTO);
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResponse> authorize(@RequestBody AuthorizeResponseDTO authorizeResponseDTO) {
        System.out.println(authorizeResponseDTO);
        return integrationApiService.authorize(authorizeResponseDTO);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferResponseDTO transferResponseDTO) {
        return integrationApiService.transfer(transferResponseDTO);

    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelResponse> cancel(@RequestBody CancelResponseDTO cancelResponseDTO) {
        System.out.println("Cancel request received: " + LocalDateTime.now() + cancelResponseDTO);
        return integrationApiService.cancel(cancelResponseDTO);
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

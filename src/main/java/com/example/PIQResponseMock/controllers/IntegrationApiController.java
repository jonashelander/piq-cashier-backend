package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.responses.*;
import com.example.PIQResponseMock.services.IntegrationApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/paymentiq", produces = "application/json;charset=utf8")
public class IntegrationApiController {


    IntegrationApiService integrationApiService = new IntegrationApiService();

    @PostMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyUser(@RequestBody VerifyUserDTO verifyUserDTO) {
        return integrationApiService.verifyUser(verifyUserDTO);
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResponse> authorize(@RequestBody AuthorizeDTO authorizeDTO) {
        return integrationApiService.authorize(authorizeDTO);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferDTO transferDTO) {
        return integrationApiService.transfer(transferDTO);

    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelResponse> cancel(CancelDTO cancelDTO) {
        return integrationApiService.cancel(cancelDTO);
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationResponse> notification(NotificationDTO notificationDTO) {
        return integrationApiService.notification(notificationDTO);
    }

    @PostMapping("/lookupuser")
    public ResponseEntity<LookupUserResponse> lookupuser(LookupUserDTO lookupUserDTO) {
        return integrationApiService.lookupUser(lookupUserDTO);
    }

}

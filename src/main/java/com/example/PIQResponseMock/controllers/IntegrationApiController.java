package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.VerifyUserDTO;
import com.example.PIQResponseMock.responses.*;
import com.example.PIQResponseMock.services.IntegrationApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/paymentiq", produces = "application/json;charset=utf8")
public class IntegrationApiController {


    @PostMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyUser(@RequestBody VerifyUserDTO verifyUserDTO) {

        return IntegrationApiService.verifyUser(verifyUserDTO);

    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizeResponse> authorize() {

        AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                "JonasSE",
                "True",
                1234,
                "01010",
                23,
                "Something went wrong",
                "Should this be an Object instead?"
        );

        return new ResponseEntity(authorizeResponse, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer() {
        TransferResponse transferResponse = new TransferResponse(
                "JonasSE",
                true,
                "12345",
                54321,
                401,
                "Bad program"
        );
        return new ResponseEntity(transferResponse, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelResponse> cancel() {
        CancelResponse cancelResponse = new CancelResponse(
                "JonasSE",
                true,
                502,
                "Something went wrong"
        );
        return new ResponseEntity(cancelResponse, HttpStatus.OK);
    }

    @PostMapping("/notification")
    public ResponseEntity<NotificationResponse> notification() {
        NotificationResponse notificationResponse = new NotificationResponse(
                true,
                400,
                "Something went wrong"
        );
        return new ResponseEntity(notificationResponse, HttpStatus.OK);
    }

    @PostMapping("/lookupuser")
    public ResponseEntity<LookupUserResponse> lookupuser() {
        LookupUserResponse lookupUserResponse = new LookupUserResponse(
                "Jonas",
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

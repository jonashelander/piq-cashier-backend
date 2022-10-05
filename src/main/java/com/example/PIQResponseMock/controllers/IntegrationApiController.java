


package com.example.PIQResponseMock.controllers;


import com.example.PIQResponseMock.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/paymentiq")
public class IntegrationApiController {


    @PostMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyuser() {

        VerifyUserResponse response = new VerifyUserResponse(
                "JonasSE",
                true,
                "VIP_SE",
                "VIP",
                "male",
                "Jonas",
                "Helander",
                "Praktejdervägen 13",
                "Stockholm",
                "sthml",
                "18461",
                "SWE",
                "helanderjonas@gmail.com",
                "1987-06-29",
                "070-9660528",
                100.5,
                "EUR",
                "sv_SE",
                new Attributes(
                        "something1",
                        "something2"
                ),
                "true",
                404,
                "USER_NOT_FOUND"
        );

        return new ResponseEntity(response, HttpStatus.OK);
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

}

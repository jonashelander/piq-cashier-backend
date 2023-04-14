package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.loggers.VerifyUserLog;
import com.example.PIQResponseMock.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntegrationApiService {
    public ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserDTO verifyUserDTO) {

        VerifyUserResponse verifyUserResponse = new VerifyUserResponse(
                "JonasEUR",
                true,
                "VIP_SE",
                "VIP",
                "MALE",
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
        VerifyUserLog verifyUserLog = new VerifyUserLog(verifyUserDTO.getSessionId(), verifyUserDTO.getUserId());

        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }

    public ResponseEntity<AuthorizeResponse> authorize(AuthorizeDTO authorizeDTO) {

        AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                "JonasEUR",
                true,
                1234,
                "01010",
                23,
                "Something went wrong"
        );
        return new ResponseEntity(authorizeResponse, HttpStatus.OK);
    }

    public ResponseEntity<TransferResponse> transfer(TransferDTO transferDTO) {
        TransferResponse transferResponse = new TransferResponse(
                "JonasEUR",
                true,
                "12345",
                54321,
                401,
                "Bad program"
        );
        return new ResponseEntity(transferResponse, HttpStatus.OK);
    }

    public ResponseEntity<CancelResponse> cancel(CancelDTO cancelDTO) {
        CancelResponse cancelResponse = new CancelResponse(
                "JonasEUR",
                true,
                502,
                "Something went wrong"
        );
        return new ResponseEntity(cancelResponse, HttpStatus.OK);
    }

    public ResponseEntity<NotificationResponse> notification(NotificationDTO notificationDTO) {
        NotificationResponse notificationResponse = new NotificationResponse(
                true,
                400,
                "Something went wrong"
        );
        return new ResponseEntity(notificationResponse, HttpStatus.OK);
    }

    public ResponseEntity<LookupUserResponse> lookupUser(LookupUserDTO lookupUserDTO) {
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

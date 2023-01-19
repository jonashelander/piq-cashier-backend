package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.VerifyUserDTO;
import com.example.PIQResponseMock.responses.Attributes;
import com.example.PIQResponseMock.responses.VerifyUserResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntegrationApiService {
    public static ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserDTO verifyUserDTO) {

        VerifyUserResponse verifyUserResponse = new VerifyUserResponse(
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
        System.out.println(verifyUserDTO.getUserId());
        System.out.println(verifyUserDTO.getSessionId());
        ResponseEntity responseEntity = new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
        //responseEntity.getHeaders().add("Content-Type", "text/html; charset=utf-8");
        return responseEntity;

    }
}

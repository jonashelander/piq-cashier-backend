


package com.example.PIQResponseMock.Controllers;


import com.example.PIQResponseMock.Responses.Attributes;
import com.example.PIQResponseMock.Responses.VerifyUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class controller {


    @GetMapping("/verifyuser")
    public ResponseEntity<VerifyUserResponse> verifyuser() {

        VerifyUserResponse res = new VerifyUserResponse();
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
                "en",
                new Attributes(
                    "something1",
                    "something2"
                ),
                "true",
                404,
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

}

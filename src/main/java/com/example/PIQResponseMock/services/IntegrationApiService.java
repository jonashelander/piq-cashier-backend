package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.models.User;
import com.example.PIQResponseMock.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IntegrationApiService {
    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService();

    public VerifyUserResponse buildVerifyUserResponse(VerifyUserDTO verifyUserDTO) {
        User user = userRepository.getUserById(verifyUserDTO.getUserId());
        AuthDTO authDTO = new AuthDTO(user.getUserId(), user.getSessionId());
        boolean activeSession = userService.authUser(authDTO);

        if (activeSession) {
            return new VerifyUserResponse(
                    user.getUserId(),
                    activeSession,
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
                    user.getPhone(),
                    user.getBalance(),
                    user.getBalanceCy(),
                    "sv_SE",
                    new Attributes(
                            "something1",
                            "something2"
                    )
            );
        } else
            return new VerifyUserResponse(
                    user.getUserId(),
                    activeSession,
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
                    user.getPhone(),
                    user.getBalance(),
                    user.getBalanceCy(),
                    "sv_SE",
                    new Attributes(
                            "something1",
                            "something2"
                    ),
                    01,
                    "User not verified"
            );
    }

    public ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserDTO verifyUserDTO) {
        VerifyUserResponse verifyUserResponse = buildVerifyUserResponse(verifyUserDTO);
        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }

    public ResponseEntity<AuthorizeResponse> authorize(AuthorizeDTO authorizeDTO) {
        //Need the the userId which we have, and also the sessionId some how. Maybe fetch the user,
        User user = userRepository.getUserById(authorizeDTO.getUserId());
        //and check for an existing session
        boolean userSession = userService.authUser(authorizeDTO.getUserId(), user.getSessionId);

        //If CC Withdrawal
        if (authorizeDTO.getTxTypeId().equals("creditcardWithdrawal")) {

            boolean userActivated = userService.checkIfUserActive(authorizeDTO.getUserId());
            boolean hasEnoughFunds = userService.checkBalance(authorizeDTO.getUserId(), authorizeDTO.getTxAmount());
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
        //If CC Deposit
        else if (authorizeDTO.getTxTypeId().equals("creditcardDeposit")) {
            boolean userActivated = userService.checkIfUserActive(authorizeDTO.getUserId());

            //build response
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

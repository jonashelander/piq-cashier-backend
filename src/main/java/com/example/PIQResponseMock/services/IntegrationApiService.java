package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.models.User;
import com.example.PIQResponseMock.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IntegrationApiService {
    UserRepository userRepository = new UserRepository();
    AuthService authService = new AuthService();

    public VerifyUserResponse buildVerifyUserResponse(VerifyUserDTO verifyUserDTO) {
        User user = userRepository.getUserById(verifyUserDTO.getUserId());

        AuthDTO authDTO = new AuthDTO(verifyUserDTO.getUserId(), verifyUserDTO.getSessionId());

        boolean isActivated = authService.checkIfActivated(verifyUserDTO.getUserId());
        boolean sessionActive;

        if (authService.authUser(authDTO).getStatusCode().equals(org.springframework.http.HttpStatus.OK)) {
            sessionActive = true;
        } else sessionActive = false;

        if (sessionActive && isActivated) {
            return new VerifyUserResponse(
                    user.getUserId(),
                    true,
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
        } else if (!isActivated) {
            return new VerifyUserResponse(
                    user.getUserId(),
                    false,
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
                    02,
                    "The user is blocked"
            );
        }
        return new VerifyUserResponse(
                user.getUserId(),
                false,
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
                "No active session found"
        );
    }

    public ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserDTO verifyUserDTO) {
        VerifyUserResponse verifyUserResponse = buildVerifyUserResponse(verifyUserDTO);
        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }

    public ResponseEntity<AuthorizeResponse> authorize(AuthorizeDTO authorizeDTO) {
        User user = userRepository.getUserById(authorizeDTO.getUserId());

        if (authorizeDTO.getTxName().equals("CreditcardWithdrawal")) {
            if (authService.checkBalance(authorizeDTO.getUserId(), authorizeDTO.getTxAmount())) {
                AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                        user.getUserId(),
                        true,
                        UUID.randomUUID().toString());
                return new ResponseEntity(authorizeResponse, HttpStatus.OK);
            } else {
                AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                        user.getUserId(),
                        false,
                        UUID.randomUUID().toString(),
                        1,
                        "Not enough funds");
                return new ResponseEntity(authorizeResponse, HttpStatus.OK);
            }
        } else if (authorizeDTO.getTxName().equals("CreditcardDeposit")) {
            AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                    user.getUserId(),
                    true,
                    UUID.randomUUID().toString());
            return new ResponseEntity(authorizeResponse, HttpStatus.OK);
        }

        AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                user.getUserId(),
                false,
                UUID.randomUUID().toString(),
                02,
                "User is blocked");
        return new ResponseEntity(authorizeResponse, HttpStatus.OK);
    }

    public ResponseEntity<TransferResponse> transfer(TransferDTO transferDTO) {
        User user = userRepository.getUserById(transferDTO.getUserId());
        double convertedTxAmount;
        try {
            convertedTxAmount = Double.parseDouble(transferDTO.getTxAmount());

        } catch (NumberFormatException e) {
            convertedTxAmount = 0;
        }
        user.setBalance(user.getBalance() + convertedTxAmount);
        TransferResponse transferResponse = new TransferResponse(
                user.getUserId(),
                true,
                transferDTO.getTxId()
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

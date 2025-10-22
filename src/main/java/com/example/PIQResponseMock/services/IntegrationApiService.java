package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.helpers.Convert;
import com.example.PIQResponseMock.model.Cancel;
import com.example.PIQResponseMock.model.Transaction;
import com.example.PIQResponseMock.repositories.AuthorizeRepository;
import com.example.PIQResponseMock.repositories.TransactionRepository;
import com.example.PIQResponseMock.repositories.TransferRepository;
import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.model.User;
import com.example.PIQResponseMock.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

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

    public ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserResponseDTO verifyUserResponseDTO) {
        VerifyUserResponse verifyUserResponse = buildVerifyUserResponse(verifyUserResponseDTO);
        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }

    public VerifyUserResponse buildVerifyUserResponse(VerifyUserResponseDTO verifyUserResponseDTO) {
        User user = userRepository.findByUserId(verifyUserResponseDTO.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new VerifyUserResponse(
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
                new Attributes(
                        "something1",
                        "something2"
                )
        );

    }

    public ResponseEntity<AuthorizeResponse> authorize(AuthorizeResponseDTO authorizeResponseDTO) {
        User user = userRepository.findByUserId(authorizeResponseDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("Authorize not found"));

        AuthorizeResponse authorize = new AuthorizeResponse(
                user.getAuthorize().getUserId(),
                user.getAuthorize().isSuccess(),
                user.getAuthorize().getAuthCode(),
                user.getAuthorize().getErrCode(),
                user.getAuthorize().getErrMsg()
        );
        return new ResponseEntity<>(authorize, HttpStatus.OK);
    }

    public ResponseEntity<TransferResponse> transfer(TransferResponseDTO transferResponseDTO) {
        User user = userRepository.findByUserId(transferResponseDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        TransferResponse transferResponse = new TransferResponse(
                user.getTransfer().getUserId(),
                user.getTransfer().isSuccess(),
                user.getTransfer().getTxId(),
                user.getTransfer().getMerchantTxId(),
                user.getTransfer().getErrCode(),
                user.getTransfer().getErrMsg()
        );
            return ResponseEntity.ok(transferResponse);
    }

    public ResponseEntity<CancelResponse> cancel(CancelResponseDTO cancelResponseDTO) {
        User user = userRepository.findByUserId(cancelResponseDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        CancelResponse cancelResponse = new CancelResponse(
                user.getCancel().getUserId(),
                user.getCancel().isSuccess(),
                user.getCancel().getErrCode(),
                user.getCancel().getErrMsg()
        );

        return ResponseEntity.ok(cancelResponse);
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

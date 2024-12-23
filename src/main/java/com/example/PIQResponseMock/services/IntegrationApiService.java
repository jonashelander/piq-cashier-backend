package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.helpers.Convert;
import com.example.PIQResponseMock.model.Transaction;
import com.example.PIQResponseMock.repositories.TransactionRepository;
import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.*;
import com.example.PIQResponseMock.model.User;
import com.example.PIQResponseMock.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class IntegrationApiService {
    UserRepository userRepository;
    AuthService authService;
    TransactionService transactionService;
    TransactionRepository transactionRepository;

    @Autowired
    public IntegrationApiService(UserRepository userRepository, AuthService authService, TransactionService transactionService, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<VerifyUserResponse> verifyUser(VerifyUserDTO verifyUserDTO) {
        VerifyUserResponse verifyUserResponse = buildVerifyUserResponse(verifyUserDTO);
        return new ResponseEntity<>(verifyUserResponse, HttpStatus.OK);
    }

    public VerifyUserResponse buildVerifyUserResponse(VerifyUserDTO verifyUserDTO) {
        User user = userRepository.findById(verifyUserDTO.getUserId()).get();
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

    public ResponseEntity<AuthorizeResponse> authorize(AuthorizeDTO authorizeDTO) {
        User user = userRepository.findById(authorizeDTO.getUserId()).get();
        Transaction transaction = transactionService.createTransaction(authorizeDTO);
        double calculatedBalance = authService.checkBalance(authorizeDTO.getUserId(), authorizeDTO.getTxAmount());
        if (authorizeDTO.getTxName().equals("CreditcardWithdrawal")) {
            if (calculatedBalance >= 0) {
                AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                        user.getUserId(),
                        true,
                        transaction.getAuthCode());
                user.setBalance(calculatedBalance);
                userRepository.save(user);
                return new ResponseEntity(authorizeResponse, HttpStatus.OK);
            } else {
                AuthorizeResponse authorizeResponse = new AuthorizeResponse(
                        user.getUserId(),
                        false,
                        UUID.randomUUID().toString(),
                        01,
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
        User user = userRepository.findById(transferDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (transferDTO.getTxName().equals("CreditcardWithdrawal")) {
            Transaction transaction = transactionRepository.findById(transferDTO.getAuthCode())
                    .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
            TransferResponse transferResponse = new TransferResponse(
                    user.getUserId(),
                    true,
                    transferDTO.getTxId()
            );
            transaction.setFinalized(true);
            transactionRepository.save(transaction);
            return new ResponseEntity<>(transferResponse, HttpStatus.OK);
        }

        double convertedTxAmount;
        try {
            convertedTxAmount = Double.parseDouble(transferDTO.getTxAmount());
        } catch (NumberFormatException e) {
            convertedTxAmount = 0;
        }

        double newBalance = user.getBalance() + convertedTxAmount;
        user.setBalance(Convert.twoDecimals(newBalance));
        userRepository.save(user);
        TransferResponse transferResponse = new TransferResponse(
                user.getUserId(),
                true,
                transferDTO.getTxId()
        );
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

    public ResponseEntity<CancelResponse> cancel(CancelDTO cancelDTO) {
        User user = userRepository.findById(cancelDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Transaction transaction = transactionRepository.findById(cancelDTO.getAuthCode()).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
        if (cancelDTO.getTxName().equals("CreditcardWithdrawal")) {
            //Need to reverse the amount since this is giving the user back the amount?
            double convertedTxAmount;
            try {
                convertedTxAmount = Double.parseDouble(cancelDTO.getTxAmount());
            } catch (NumberFormatException e) {
                convertedTxAmount = 0;
            }
            double newBalance = user.getBalance() - convertedTxAmount;
            user.setBalance(Convert.twoDecimals(newBalance));
            userRepository.save(user);
            transaction.setFinalized(true);
            transactionRepository.save(transaction);
            CancelResponse cancelResponse = new CancelResponse(
                    user.getUserId(),
                    true,
                    05,
                    "No errors"
            );
            return new ResponseEntity(cancelResponse, HttpStatus.OK);
        }
        transaction.setFinalized(true);
        transactionRepository.save(transaction);
        CancelResponse cancelResponse = new CancelResponse(
                cancelDTO.getUserId(),
                true,
                01,
                "Nothing went wrong"
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

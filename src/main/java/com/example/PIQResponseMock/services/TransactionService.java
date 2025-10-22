package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.AuthorizeResponseDTO;
import com.example.PIQResponseMock.model.Transaction;
import com.example.PIQResponseMock.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    AuthService authService;
    TransactionRepository transactionRepository;

    public TransactionService(AuthService authService, TransactionRepository transactionRepository) {
        this.authService = authService;
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(AuthorizeResponseDTO authorizeResponseDTO) {
        Transaction transaction = new Transaction(authorizeResponseDTO.getUserId(), authorizeResponseDTO.getTxName(), authService.convertTxAmount(authorizeResponseDTO.getTxAmount()));
        transactionRepository.save(transaction);
        return transaction;
    }
}

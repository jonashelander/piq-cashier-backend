package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.AuthorizeDTO;
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

    public Transaction createTransaction(AuthorizeDTO authorizeDTO) {
        Transaction transaction = new Transaction(authorizeDTO.getUserId(), authorizeDTO.getTxName(), authService.convertTxAmount(authorizeDTO.getTxAmount()));
        transactionRepository.save(transaction);
        return transaction;
    }
}

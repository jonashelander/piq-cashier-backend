package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction getTransactionByAuthCode(String Authcode);
}

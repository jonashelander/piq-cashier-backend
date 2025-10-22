package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, String> {
}

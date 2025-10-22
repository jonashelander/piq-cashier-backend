package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.Cancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancelRepository extends JpaRepository<Cancel, String> {
}

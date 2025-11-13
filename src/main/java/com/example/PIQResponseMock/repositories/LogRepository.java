package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.VerifyUserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<VerifyUserLog, String> {
}

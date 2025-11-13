package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.AuthorizeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizeLogRepository extends JpaRepository<AuthorizeLog, String> {
}


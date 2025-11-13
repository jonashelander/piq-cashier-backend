package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.model.VerifyUserLog;
import com.example.PIQResponseMock.repositories.LogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LogService {
    LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public ResponseEntity<VerifyUserLog> findVerifyUserLogById(String id) {
       VerifyUserLog verifyuserLog =  logRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Log not found for id: " + id));

         return ResponseEntity.ok(verifyuserLog);
    }
}

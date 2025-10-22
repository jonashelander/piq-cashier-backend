package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.Authorize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizeRepository extends JpaRepository<Authorize, String> {
}



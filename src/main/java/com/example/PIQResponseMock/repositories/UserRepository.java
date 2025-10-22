package com.example.PIQResponseMock.repositories;

import com.example.PIQResponseMock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
}

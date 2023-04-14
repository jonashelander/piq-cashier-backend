package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseEntity<String> signUp(UserDTO userDTO) {
        System.out.println(userDTO);
        //User user = new User();
        //userRepository.save(user);

        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
}

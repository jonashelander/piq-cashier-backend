package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.helpers.Converter;
import com.example.PIQResponseMock.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    UserRepository userRepository = new UserRepository();

    public UserDTO signUp(SignUpDTO signUpDTO) {
        User user = new User(
                signUpDTO.getFirstName(),
                signUpDTO.getLastName(),
                signUpDTO.getDob(),
                signUpDTO.getSex(),
                signUpDTO.getCountry(),
                signUpDTO.getCity(),
                signUpDTO.getState(),
                signUpDTO.getStreet(),
                signUpDTO.getZip(),
                signUpDTO.getPhone(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword()
        );
        userRepository.save(user);
        UserDTO userDTO = Converter.convertUserToDTO(user);
        return userDTO;
    }

    public ResponseEntity<UserDTO> signIn(SignInDTO signInDTO) {
        User user = userRepository.getUserBySignInDTO(signInDTO);

        user.setSessionId(UUID.randomUUID().toString());
        UserDTO userDTO = Converter.convertUserToDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public void signOut(String userId) {
        User user = userRepository.getUserById(userId);
        user.setSessionId(null);
        userRepository.updateUserById(user);
    }

    public ResponseEntity authUser(AuthDTO authDTO) {
        User user = userRepository.getUserById(authDTO.getUserId());
        if (user.getSessionId() == null || !user.getSessionId().equals(authDTO.getSessionId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDTO userDTO = Converter.convertUserToDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public boolean checkBalance(String userId, String txAmount) {
        int convertedAmount;
        try {
            convertedAmount = Integer.parseInt(txAmount);
        } catch (NumberFormatException e) {
            convertedAmount = 0;
        }

        User user = userRepository.getUserById(userId);
        double result = user.getBalance() - convertedAmount;

        return result >= 0;
    }

    public boolean checkIfBlocked(String userId) {
        User user = userRepository.getUserById(userId);
        return user.isBlocked();
    }

    public ResponseEntity<Boolean> blockUser(String userId) {
        User user = userRepository.getUserById(userId);
        user.setBlocked(true);

        return new ResponseEntity<>(user.isBlocked(), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> unBlockUser(String userId) {
        User user = userRepository.getUserById(userId);

        user.setBlocked(false);
        return new ResponseEntity<>(user.isBlocked(), HttpStatus.OK);
    }
}

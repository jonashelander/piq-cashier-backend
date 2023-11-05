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

import java.util.Objects;
import java.util.UUID;

//REFACTOR TO RETURN RESPONSE ENTITY ALREADY IN SERVICE ON ALL METHODS
@Service
public class UserService {
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

    //Strings are most likely not matched correctly, since it will return true even if the sessionId's does not match.
    public boolean authUser(AuthDTO authDTO) {
        User user = userRepository.getUserById(authDTO.getUserId());
        if (user.getSessionId() == null) {
            return false;
        } else if (user.getSessionId().equals(authDTO.getSessionId())) {
            System.out.println(user.getSessionId() + " = " + authDTO.getSessionId());
            return true;
        }
        return false;
    }

    public boolean checkBalance(String userId, String txAmount) {
        int convertedAmount;
        try {
            convertedAmount = Integer.parseInt(txAmount);
        } catch (NumberFormatException e) {
            convertedAmount = 0;
        }

        User user = userRepository.getUserById(userId);
        int result = user.getBalance() - convertedAmount;

        if (result >= 0) {
            return true;
        } else return false;
    }

    public boolean checkIfBlocked(String userId) {
        User user = userRepository.getUserById(userId);
        if (user.isBlocked()) {
            return true;
        }
        return false;
    }
}

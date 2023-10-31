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

//REFACTOR TO RETURN RESPONSE ENTITY ALREADY IN SERVICE ON ALL METHODS
@Service
public class UserService {
    UserRepository userRepository = new UserRepository();

    //Creates a new user with inputs from the frontend
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

    //Finds the user and creates a new session.
    public ResponseEntity<UserDTO> signIn(SignInDTO signInDTO) {
        User user = userRepository.getUserBySignInDTO(signInDTO);

        user.setSessionId(UUID.randomUUID().toString());
        UserDTO userDTO = Converter.convertUserToDTO(user);

        System.out.println(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public void signOut(String userId) {
        User user = userRepository.getUserById(userId);
        user.setSessionId(null);
        //Update user in database
        userRepository.updateUserById(user);
    }

    public boolean authUser(AuthDTO authDTO) {
        User user = userRepository.getUserById(authDTO.getUserId());
        if (user.getSessionId().equals(authDTO.getSessionId())) {
            return true;
        }
        return false;
    }

    public boolean checkBalance(String userId, String txAmount) {
        //convert the incoming txAmount string to an int.
        int convertedAmount;
        try {
            convertedAmount = Integer.parseInt(txAmount);
        } catch (NumberFormatException e) {
            convertedAmount = 0;
        }

        //find the user
        User user = userRepository.getUserById(userId);

        //check that the user has enough funds
        if (user.getBalance() >= convertedAmount) {
            return true;
        } else return false;
    }
}

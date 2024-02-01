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


import java.util.List;
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
                signUpDTO.getPassword(),
                true,
                "New member"
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
        double convertedAmount;
        try {
            convertedAmount = Double.parseDouble(txAmount);
        } catch (NumberFormatException e) {
            convertedAmount = 0.00;
        }
        User user = userRepository.getUserById(userId);
        double result = user.getBalance() + convertedAmount;
        return result >= 0;
    }

    public boolean checkIfBlocked(String userId) {
        User user = userRepository.getUserById(userId);
        return user.isActivated();
    }

    public ResponseEntity<Boolean> blockUser(String userId) {
        User user = userRepository.getUserById(userId);
        user.setActivated(true);

        return new ResponseEntity<>(user.isActivated(), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> unBlockUser(String userId) {
        User user = userRepository.getUserById(userId);

        user.setActivated(false);
        return new ResponseEntity<>(user.isActivated(), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getUsers() {
        List users = userRepository.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        User user = userRepository.getUserById(userDTO.getUserId());

        user.setUserId(userDTO.getUserId());
        user.setSessionId(userDTO.getSessionId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDob(userDTO.getLastName());
        user.setSex(userDTO.getSex());
        user.setCountry(userDTO.getCountry());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setStreet(userDTO.getStreet());
        user.setZip(userDTO.getZip());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setBalance(userDTO.getBalance());
        user.setBalanceCy(userDTO.getBalanceCy());
        user.setActivated(userDTO.isActivated());

        System.out.println(userDTO.isActivated());
        userRepository.save(user);

        UserDTO updatedUserDTO = Converter.convertUserToDTO(user);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }
}

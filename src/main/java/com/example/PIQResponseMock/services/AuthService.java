package com.example.PIQResponseMock.services;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.helpers.Convert;
import com.example.PIQResponseMock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return Convert.convertUserToDTO(user);
    }

    public ResponseEntity<UserDTO> signIn(SignInDTO signInDTO) {
        User user = userRepository.getUserByEmail(signInDTO.getEmail());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setSessionId(UUID.randomUUID().toString());
        userRepository.save(user);
        UserDTO userDTO = Convert.convertUserToDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    public void signOut(String userId) {
        userRepository.findById(userId).ifPresent(user -> {
            user.setSessionId(null);
            userRepository.save(user);
        });
    }

    public ResponseEntity<?> authUser(AuthDTO authDTO) {
        return userRepository.findById(authDTO.getUserId())
                .map(user -> {
                    if (user.getSessionId() == null || !user.getSessionId().equals(authDTO.getSessionId())) {
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                    UserDTO userDTO = Convert.convertUserToDTO(user);
                    return ResponseEntity.ok(userDTO);
                })
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    public double checkBalance(String userId, String txAmount) {
        return userRepository.findById(userId)
                .map(user -> {
                    double convertedAmount;
                    try {
                        convertedAmount = Double.parseDouble(txAmount);
                    } catch (NumberFormatException e) {
                        convertedAmount = 0.00;
                    }
                    return user.getBalance() + convertedAmount;
                })
                .orElse(0.00);
    }

    public boolean checkIfActivated(String userId) {
        return userRepository.findById(userId)
                .map(User::isActivated)
                .orElse(false);
    }

    public ResponseEntity<Boolean> blockUser(String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setActivated(false);
                    userRepository.save(user);
                    return ResponseEntity.ok(user.isActivated());
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Boolean> unBlockUser(String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setActivated(true);
                    userRepository.save(user);
                    return ResponseEntity.ok(user.isActivated());
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        return userRepository.findById(userDTO.getUserId())
                .map(user -> {
                    user.setUserId(userDTO.getUserId());
                    user.setFirstName(userDTO.getFirstName());
                    user.setLastName(userDTO.getLastName());
                    user.setDob(userDTO.getDob());
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
                    userRepository.save(user);
                    return ResponseEntity.ok(Convert.convertUserToDTO(user));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<UserDTO> getUserById(String userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(Convert.convertUserToDTO(user)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

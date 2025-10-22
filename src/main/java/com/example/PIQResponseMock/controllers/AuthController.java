package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignUpResponseDTO;
import com.example.PIQResponseMock.dto.SignInResponseDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.model.User;
import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf8")
//@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
public class AuthController {
    AuthService authService;
    UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpResponseDTO signUpResponseDTO) {
        UserDTO userDTO = authService.signUp(signUpResponseDTO);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> singIn(@RequestBody SignInResponseDTO signInResponseDTO) {
        return authService.signIn(signInResponseDTO);
    }

    @PostMapping("/{userId}")
    public void signOut(@PathVariable("userId") String userId) {
        authService.signOut(userId);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthDTO authDTO) {
        return authService.authUser(authDTO);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return authService.getUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        return authService.updateUser(id, userDTO);
    }

    @GetMapping("/checkDuplicate/{userId}")
    public ResponseEntity<?> checkDuplicate(@PathVariable String userId) {
        boolean exists = userRepository.existsByUserId(userId.trim());
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "UserId already exists"));
        }
        return ResponseEntity.ok(Map.of("message", "UserId is available"));
    }
}

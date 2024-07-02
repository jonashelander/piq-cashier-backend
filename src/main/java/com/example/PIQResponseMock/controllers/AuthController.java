package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.model.User;
import com.example.PIQResponseMock.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf8")
//@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
public class AuthController {
    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = authService.signUp(signUpDTO);

        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> singIn(@RequestBody SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @PostMapping("/{userId}")
    public void signOut(@PathVariable("userId") String userId) {
        authService.signOut(userId);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthDTO authDTO) {
        return authService.authUser(authDTO);
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<Boolean> blockUser(@PathVariable String userId) {
        return authService.blockUser(userId);
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<Boolean> unBlockUser(@PathVariable String userId) {
        return authService.unBlockUser(userId);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        return authService.getUserById(userId);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return authService.getUsers();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        return authService.updateUser(userDTO);
    }

}

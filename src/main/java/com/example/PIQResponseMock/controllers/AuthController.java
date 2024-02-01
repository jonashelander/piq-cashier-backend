package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.models.User;
import com.example.PIQResponseMock.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf8")
//@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
public class AuthController {
    //test comment
    AuthService authService = new AuthService();

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
    public ResponseEntity<Boolean> authenticateUser(@RequestBody AuthDTO authDTO) {
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

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return authService.getUsers();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        return authService.updateUser(userDTO);
    }
}

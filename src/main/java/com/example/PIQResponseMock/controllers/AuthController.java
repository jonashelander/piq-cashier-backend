package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.AuthDTO;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.models.User;
import com.example.PIQResponseMock.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf8")
public class AuthController {

    UserService userService = new UserService();

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO userDTO = userService.signUp(signUpDTO);

        return ResponseEntity.ok().body(userDTO);
        //return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> singIn(@RequestBody SignInDTO signInDTO) {
        return userService.signIn(signInDTO);
    }

    @PostMapping("/{userId}")
    public void signOut(@PathVariable("userId") String userId) {
        System.out.println("auth controller signout triggered");
        System.out.println("incoming userId is: " + userId);
        userService.signOut(userId);
    }

    @PostMapping("/auth")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody AuthDTO authDTO) {
        System.out.println("in controller log AuthDTO " + authDTO);
        Boolean userAuthenticated = userService.authUser(authDTO);
        return ResponseEntity.ok().body(userAuthenticated);
    }
}

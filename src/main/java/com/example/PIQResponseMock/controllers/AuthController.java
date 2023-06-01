package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.SignInDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/user", produces = "application/json;charset=utf8")
public class UserController {

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

    @PostMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

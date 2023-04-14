package com.example.PIQResponseMock.controllers;

import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user")
public class UserController {

    UserService userService = new UserService();

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        ResponseEntity<String> user = userService.signUp(userDTO);
        return user;
    }
}

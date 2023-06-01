package com.example.PIQResponseMock;

import com.example.PIQResponseMock.Repositories.UserRepository;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.dto.UserDTO;
import com.example.PIQResponseMock.models.User;
import com.example.PIQResponseMock.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PiqResponseMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiqResponseMockApplication.class, args);
        UserService userService = new UserService();
        UserRepository userRepository = new UserRepository();

        SignUpDTO user = new SignUpDTO(
                "Jonas",
                "Helander",
                "1987-06-29",
                "MALE",
                "SWE",
                "Stockholm",
                "Stockholm",
                "Praktejdervägen 13",
                "184 61",
                "+46709660528",
                "user",
                "pass"
        );

        userService.signUp(user);
        userRepository.findAll();
    }

}

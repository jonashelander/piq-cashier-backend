package com.example.PIQResponseMock;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

/*@EnableSwagger2*/
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

/*    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
    }*/

}

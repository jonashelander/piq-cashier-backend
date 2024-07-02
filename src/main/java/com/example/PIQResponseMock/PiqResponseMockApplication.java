package com.example.PIQResponseMock;

import com.example.PIQResponseMock.repositories.UserRepository;
import com.example.PIQResponseMock.dto.SignUpDTO;
import com.example.PIQResponseMock.services.AuthService;
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
    }
}

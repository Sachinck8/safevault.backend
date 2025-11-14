package com.safevault.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig {

     @Bean
     public OpenAPI jcryptOpenAPI(){
        return new OpenAPI()
        .info(new Info()
        .title("safevaulte API")
        .description("backend api for aes, des, rsa")
        .version("1.0.0")
        );
     }

}

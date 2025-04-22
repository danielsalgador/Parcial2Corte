package com.example.PARCIAL2CORTE.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info((new Info()
                        .title("API Parcial")
                        .version("1.0")
                        .description("Documentacion de la API para reportes de Libreria en SupBase")
                        .contact(new Contact()
                                .name("Daniel Salgado")
                                .email("danielsalgador@gmail.com"))));
    }
}

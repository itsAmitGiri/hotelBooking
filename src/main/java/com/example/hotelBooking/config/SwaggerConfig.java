package com.example.hotelBooking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Booking API")
                        .version("1.0")
                        .description("API documentation for Room Booking Service")
                        .contact(new Contact().name("Amit Giri").email("amit@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}

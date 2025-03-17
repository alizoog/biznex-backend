package com.biznex.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "BIZNEX",
                version = "1.0",
                contact = @Contact(
                        name = "Ibrokhim Khamidov", email = "ibrokhimali786@gmail.com"
                ),
                description = "biznex services"
        ),
        servers = {
                @Server(url = "http://localhost:${server.port}", description = "Local development")
        }
)

@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}

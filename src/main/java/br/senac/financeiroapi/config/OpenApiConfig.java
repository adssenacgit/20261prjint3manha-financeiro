package br.senac.financeiroapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI financeiroOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Financeiro API")
                        .description("API REST para CRUD de empresa, usuário, categoria, conta e transação.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Senac RJ")
                                .email("suporte@exemplo.com")));
    }
}

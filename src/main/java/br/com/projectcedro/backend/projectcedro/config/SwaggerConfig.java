package br.com.projectcedro.backend.projectcedro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String CONTATO_EMAIL = "luisfelipebr1995@gmail.com";
    private static final String CONTATO_URL = "https://kanawagainc.com";
    private static final String CONTATO_NOME = "Luis Felipe Rodrigues";
    private static final String API_VERSAO = "1.0.0";
    private static final String API_DESCRICAO = "API de Gerenciamento de Consultas e Exames";
    private static final String API_TITULO = "Ima Projetos";

    private static final String PACOTE_BASE = "br.com.projectcedro.backend.api";


    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title(API_TITULO)
                        .version(API_VERSAO)
                        .description(API_DESCRICAO)
                        .contact(new Contact()
                                .name(CONTATO_NOME)
                                .email(CONTATO_EMAIL)
                                .url(CONTATO_URL))
                        .termsOfService("https://www.kanawagainc.com.br")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.kanawagainc.com.br"))
                );
    }
}



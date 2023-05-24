package br.com.projectcedro.backend.projectcedro;

import br.com.projectcedro.backend.projectcedro.services.EmailSenderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Imma Projetos",
                version = "1.0.0",
                description = "API de Gerenciamento de Consultas e Exames",
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                contact = @Contact(
                        name = "Kanawaga",
                        email = "desenvolvimento@kanawaga.com.br",
                        url = "https://www.kanawaga.com.br"
                )
        )

)
public class ProjectCedroApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProjectCedroApplication.class, args);
    }

}

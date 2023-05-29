package br.com.projectcedro.backend.projectcedro.api.pacientes.dtos;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PacienteResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String telefone;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

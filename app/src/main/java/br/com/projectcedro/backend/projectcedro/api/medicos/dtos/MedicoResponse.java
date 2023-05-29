package br.com.projectcedro.backend.projectcedro.api.medicos.dtos;


import br.com.projectcedro.backend.projectcedro.core.entities.EspecialidadeMedica;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "firstName", "lastName", "telefone", "email", "ativo", "createdAt", "updatedAt"})
public class MedicoResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String telefone;
    private String email;
    private Boolean ativo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

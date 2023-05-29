package br.com.projectcedro.backend.projectcedro.api.consultas.dtos;


import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteRequest;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@JsonPropertyOrder({"id", "firstName", "lastName", "cpf", "dataNascimento", "telefone", "email"})
public class ConsultaResponse {


    private String id;
    private LocalDateTime data;
    private MedicoRequest medico;

    @JsonIgnoreProperties("password")
    private PacienteRequest paciente;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

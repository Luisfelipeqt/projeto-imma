package br.com.projectcedro.backend.projectcedro.api.medicos.dtos;

import br.com.projectcedro.backend.projectcedro.core.entities.EspecialidadeMedica;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class MedicoRequest {


        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @CPF
        @NotNull
        @NotEmpty
        @Size(min = 14, max = 14)
        @EqualsAndHashCode.Include
        private String cpf;

        private String crm;

        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 15, max = 15)
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Deve estar no formato (99) 99999-9999")
        private String telefone;

        @Email
        private String email;

        private EspecialidadeMedica especialidadeMedica;
}


package br.com.projectcedro.backend.projectcedro.api.pacientes.dtos;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PacienteRequest {


        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 3, max = 80)
        private String firstName;

        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 3, max = 80)
        private String lastName;

        @CPF
        @NotNull
        @NotEmpty
        @Size(min = 14, max = 14)
        @EqualsAndHashCode.Include
        private String cpf;

        @Past
        @NotNull
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private Date dataNascimento;

        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 15, max = 15)
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Deve estar no formato (99) 99999-9999")
        private String telefone;

        @Email
        @Size(min = 10, max = 80)
        private String email;


        @NotNull
        @NotEmpty
        @NotBlank
        @Size(min = 5, max = 255)
        private String password;
}


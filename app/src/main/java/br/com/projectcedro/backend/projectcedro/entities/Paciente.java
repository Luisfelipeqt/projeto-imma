package br.com.projectcedro.backend.projectcedro.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_paciente")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Relation(collectionRelation = "pacientes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Paciente extends RepresentationModel<Paciente>  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String lastName;

    @CPF
    @NotNull
    @NotEmpty
    @Size(min = 14, max = 14)
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @Past
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 15, max = 15)
    @Column(nullable = false, length = 15, unique = true)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Deve estar no formato (99) 99999-9999")
    private String telefone;

    @Email
    @Size(min = 10, max = 80)
    @Column(length = 80, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "convenio_saude_id")
    private ConvenioSaude convenioSaude;
}

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
@ToString(onlyExplicitlyIncluded = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Relation(collectionRelation = "pacientes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente extends RepresentationModel<Paciente>  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(nullable = false, length = 80)
    private String lastName;

    @NotNull
    @Size(min = 14, max = 14)
    @CPF
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento", nullable = false)
    @DateTimeFormat(iso = ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private Date dataNascimento;

    @NotNull
    @Size(min = 15, max = 15)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Deve estar no formato (99) 99999-9999")
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotNull
    @Size(min = 10, max = 80)
    @Email
    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "convenio_saude_id")
    private ConvenioSaude convenioSaude;
}

package br.com.projectcedro.backend.projectcedro.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_paciente")
@JsonIgnoreProperties("password")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Relation(collectionRelation = "pacientes")

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Paciente extends Auditor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;


    @Column(nullable = false, length = 80)
    private String lastName;


    @Column(nullable = false, length = 14)
    private String cpf;


    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(length = 80)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "convenio_saude_id")
    private ConvenioSaude convenioSaude;
}

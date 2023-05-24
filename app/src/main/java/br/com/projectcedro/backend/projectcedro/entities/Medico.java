package br.com.projectcedro.backend.projectcedro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_medico")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Relation(collectionRelation = "medicos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico extends RepresentationModel<Medico> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @CPF
    @NotNull
    @NotEmpty
    @Size(min = 14, max = 14)
    @EqualsAndHashCode.Include
    @Column(nullable = false, length = 14, unique = true)
    private String cpf;

    private String crm;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 15, max = 15)
    @Column(nullable = false, length = 15, unique = true)
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "Deve estar no formato (99) 99999-9999")
    private String telefone;

    @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private EspecialidadeMedica especialidadeMedica;

    private Boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}





package br.com.projectcedro.backend.projectcedro.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
@ToString(onlyExplicitlyIncluded = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Relation(collectionRelation = "medicos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico extends RepresentationModel<Medico> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;
    private String crm;
    private String telefone;
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true)
    private EspecialidadeMedica especialidadeMedica;

    private Boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;
}





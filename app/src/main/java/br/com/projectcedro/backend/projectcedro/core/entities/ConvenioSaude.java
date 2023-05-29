package br.com.projectcedro.backend.projectcedro.core.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_convenio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConvenioSaude extends Auditor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "numero_carteirinha", nullable = false)
    private String numeroCarteira;

    @Column(name = "registro_ans", nullable = false)
    private String registroANS;

    @Column(name = "codigo_plano")
    private Integer codigoPlano;

    @Enumerated(EnumType.ORDINAL)
    private PlanosDeSaude operadora = PlanosDeSaude.NAO_HA;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_plano", nullable = false)
    private TipoPlanoSaude tipoPlano = TipoPlanoSaude.PARTICULAR;


    @ManyToOne
    @JsonIgnore
    private Paciente paciente;
}


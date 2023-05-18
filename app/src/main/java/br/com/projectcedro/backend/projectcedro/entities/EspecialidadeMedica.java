package br.com.projectcedro.backend.projectcedro.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
public enum EspecialidadeMedica {
    CLINICA_GERAL(1),
    PEDIATRIA(2),
    CARDIOLOGIA(3),
    ONCOLOGIA(4),
    ORTOPEDIA(5),
    GINECOLOGIA_OBSTETRICIA(6),
    DERMATOLOGIA(7),
    NEUROLOGIA(8),
    PSIQUIATRIA(9),
    UROLOGIA(10);

    private int code;

     EspecialidadeMedica(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public static EspecialidadeMedica valueOf(int code) {
        for (EspecialidadeMedica value : EspecialidadeMedica.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Especialidade Medica code");
    }

    }


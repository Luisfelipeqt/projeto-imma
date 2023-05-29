package br.com.projectcedro.backend.projectcedro.core.entities;

public enum TipoPlanoSaude {
    PARTICULAR(1),
    EMPRESARIAL(2),
    COLETIVO_POR_ADERENCIA(3),
    FILANTROPICO(4),
    SUS(5);

    private int code;

    TipoPlanoSaude(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public static TipoPlanoSaude valueOf(int code) {
        for (TipoPlanoSaude value : TipoPlanoSaude.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Tipo Plano Saúde code");
    }

}
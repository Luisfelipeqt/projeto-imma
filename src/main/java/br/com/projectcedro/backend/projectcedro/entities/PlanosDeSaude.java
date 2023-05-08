package br.com.projectcedro.backend.projectcedro.entities;

public enum PlanosDeSaude {
    AMIL(1),
    BRADESCO_SAÚDE(2),
    SULAMÉRICA(3),
    NOTRE_DAME_INTERMÉDICA(4),
    UNIMED(5),
    HAPVIDA(6),
    PORTO_SEGURO_SAÚDE(7),
    ALLIANZ_SAÚDE(8),
    PETROBRAS_DISTRIBUIDORA(9);

    private int code;

    PlanosDeSaude(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public static PlanosDeSaude valueOf(int code) {
        for (PlanosDeSaude value : PlanosDeSaude.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Convenvio code");
    }

}



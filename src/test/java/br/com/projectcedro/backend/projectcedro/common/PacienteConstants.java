package br.com.projectcedro.backend.projectcedro.common;

import br.com.projectcedro.backend.projectcedro.entities.Paciente;

import java.util.Date;

public class PacienteConstants {

    public static final Paciente PACIENTE = new Paciente(1L, "Luis Felipe", "Rodrigues", "058.151.473-46", new Date("13/02/1995"), "luisfelipebr1995@gmail.com", "13/02/1995");
    public static final Paciente INVALID_PACIENTE = new Paciente(0L, "", "", "", new Date("13/02/1995"), "", "");


    public static final Paciente Paciente_BY_ID = new Paciente(1L, "Luis Felipe", "Ensolarado", "Terrain", new Date("13/02/1995"), "lalalala", "asdasdsa");

}

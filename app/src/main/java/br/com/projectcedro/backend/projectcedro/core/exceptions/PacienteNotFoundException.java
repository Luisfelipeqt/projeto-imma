package br.com.projectcedro.backend.projectcedro.core.exceptions;

public class PacienteNotFoundException extends ModelNotFoundException {

    public PacienteNotFoundException() {
        super("Paciente não econtrado");
    }

    public PacienteNotFoundException(String message) {
        super(message);
    }

}

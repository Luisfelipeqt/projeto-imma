package br.com.projectcedro.backend.projectcedro.services.exceptions;


import java.io.Serial;
import java.sql.SQLIntegrityConstraintViolationException;

public class DatabaseException extends SQLIntegrityConstraintViolationException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DatabaseException(String msg) {
        super(msg);
    }
}
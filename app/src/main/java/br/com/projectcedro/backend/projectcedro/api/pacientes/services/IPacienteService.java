package br.com.projectcedro.backend.projectcedro.api.pacientes.services;

import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    List<Paciente> findAll();

    Page<Paciente> findAll(Pageable pageable);
    Optional<Paciente> findById(String id);

    Paciente create(Paciente Paciente);


    Paciente update(String id, Paciente paciente);

    void delete(String id);


    Optional<Paciente> findPacienteByCpf(String cpf);


}

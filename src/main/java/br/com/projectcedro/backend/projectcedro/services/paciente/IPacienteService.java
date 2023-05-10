package br.com.projectcedro.backend.projectcedro.services.paciente;

import br.com.projectcedro.backend.projectcedro.entities.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

    List<Paciente> findAll();

    Page<Paciente> findAll(Pageable pageable);
    Paciente findById(Long id);

    Paciente create(Paciente Paciente);


    Paciente update(Paciente paciente, Long id);

    void delete(Long id);


    boolean existsPacienteByCpf(Paciente paciente);
    boolean existsPacienteByCpf(String pacienteCpf);

    Optional<Paciente> findByCpf(String cpf);
}

package br.com.projectcedro.backend.projectcedro.api.medicos.services;

import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {

    List<Medico> findAll();


    Optional<Medico> findById(String id);

    Medico create(Medico Medico);

    Medico update(String id, Medico medico);


    void delete(String id);

    Optional<Medico> findMedicosByCpf(String cpf);
}



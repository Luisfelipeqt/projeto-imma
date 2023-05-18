package br.com.projectcedro.backend.projectcedro.services.medico;

import br.com.projectcedro.backend.projectcedro.entities.Medico;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMedicoService {

    public List<Medico> findAll();

    public Page<Medico> findAll(Pageable pageable);

    public Medico findById(Long id);

    public Medico create(Medico Medico);

    public Medico update(Medico medico, Long id);


    public void delete(Long id);
}



package br.com.projectcedro.backend.projectcedro.api.consultas.services;

import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IConsultaService {

    boolean existeConsultaAgendada(Optional<Medico> medico, LocalDateTime dataHora);
    void delete(String id);
    Consulta update(String id, Consulta consulta);
    Consulta create(Consulta consulta);
    Optional<Consulta> findById(String id);
    Page<Consulta> findAll(Pageable pageable);
    List<Consulta> findAll();
}

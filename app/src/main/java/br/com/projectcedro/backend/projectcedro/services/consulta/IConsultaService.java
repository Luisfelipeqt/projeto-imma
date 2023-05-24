package br.com.projectcedro.backend.projectcedro.services.consulta;

import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IConsultaService {

    public boolean existeConsultaAgendada(Medico medico, LocalDateTime dataHora);
    public void delete(Long id);
    public Consulta update(Long id, Consulta consulta);
    public Consulta create(Consulta consulta);
    public Consulta findById(Long id);
    public Page<Consulta> findAll(Pageable pageable);
    public List<Consulta> findAll();
}

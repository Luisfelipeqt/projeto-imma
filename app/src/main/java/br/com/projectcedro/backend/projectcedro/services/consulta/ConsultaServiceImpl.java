package br.com.projectcedro.backend.projectcedro.services.consulta;

import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.entities.Medico;
import br.com.projectcedro.backend.projectcedro.repositories.ConsultaRepository;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements IConsultaService{

    private final Logger logger = Logger.getLogger(ConsultaServiceImpl.class.getName());



    private final ConsultaRepository consultaRepository;


    public List<Consulta> findAll() {
        logger.info("Encontrando todas as consultas!");
        var entity = consultaRepository.findAll();
        return entity;
    }

    public Page<Consulta> findAll(Pageable pageable) {
        logger.info("Encontrando todas as consultas!");
        var entity = consultaRepository.findAll(pageable);
        return  entity;
    }

    public Consulta findById(Long id) {
        logger.info("Encontrando uma consulta!");
        var entity = consultaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado!"));
        return entity;
    }

    public Consulta create(Consulta consulta) {
        if (consulta == null) throw new ResourceNotFoundException("Consulta não encontrado!");
        logger.info("Criando uma consulta!");
        var entity =  consultaRepository.save(consulta);
        return entity;
    }

    public Consulta update(Long id, Consulta consulta) {
        logger.info("Atualizando uma consulta!");

        findById(id);

        return consultaRepository.save(consulta);
    }

    public void delete(Long id) {

        logger.info("Deletando uma consulta!");

        var entity = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrado!"));
        consultaRepository.delete(entity);
    }

    public boolean existeConsultaAgendada(Medico medico, LocalDateTime dataHora) {
        return consultaRepository.existsByMedicoAndData(medico, dataHora);
    }
}



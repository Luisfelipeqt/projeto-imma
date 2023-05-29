package br.com.projectcedro.backend.projectcedro.api.consultas.services;

import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.exceptions.ModelNotFoundException;
import br.com.projectcedro.backend.projectcedro.core.repositories.ConsultaRepository;
import br.com.projectcedro.backend.projectcedro.core.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements IConsultaService{

    private final Logger logger = Logger.getLogger(ConsultaServiceImpl.class.getName());


    private final PacienteRepository pacienteRepository;
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

    public Optional<Consulta> findById(String id) {
        logger.info("Encontrando uma consulta!");
        var entity = consultaRepository.findById(id);
        return entity;
    }

    public Consulta create(Consulta consulta) {
        if (consulta == null) throw new ModelNotFoundException("Consulta não encontrado!");
        logger.info("Criando uma consulta!");
        var entity =  consultaRepository.save(consulta);
        return entity;
    }


    public Consulta update(String id, Consulta consulta) {
        try {
            Consulta buscarConsulta = consultaRepository.findById(consulta.getId()).orElseThrow(() -> new ModelNotFoundException("Consulta não encontrada!"));
            buscarConsulta.setData(consulta.getData());
            buscarConsulta.setPaciente(consulta.getPaciente());
            buscarConsulta.setMedico(consulta.getMedico());
            logger.info("Atualizando uma consulta!");
            return consultaRepository.save(buscarConsulta);
        }
        catch(EntityNotFoundException e){
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean existeConsultaAgendada(Optional<Medico> medico, LocalDateTime dataHora) {
        return false;
    }

    public void delete(String id) {

        logger.info("Deletando uma consulta!");

        var entity = consultaRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ModelNotFoundException("Consulta não encontrado!"));
        consultaRepository.delete(entity);
    }
}



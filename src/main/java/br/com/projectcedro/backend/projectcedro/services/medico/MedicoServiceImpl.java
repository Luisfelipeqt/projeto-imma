package br.com.projectcedro.backend.projectcedro.services.medico;

import br.com.projectcedro.backend.projectcedro.entities.Medico;
import br.com.projectcedro.backend.projectcedro.repositories.MedicoRepository;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements IMedicoService{

    private final Logger logger = Logger.getLogger(MedicoServiceImpl.class.getName());


    private final MedicoRepository medicoRepository;


    public List<Medico> findAll() {
        logger.info("Encontrando todos os medicos!");
        var entity = medicoRepository.findAll();
        return entity;
    }

    public Page<Medico> findAll(Pageable pageable) {
        logger.info("Encontrando todas os medicos!");
        var entity = medicoRepository.findAll(pageable);
        return  entity;
    }

    public Medico findById(Long id) {
        logger.info("Encontrando um medico!");
        var entity = medicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado!"));
        return entity;
    }

    public Medico create(Medico Medico) {
        if (Medico == null) throw new ResourceNotFoundException("Medico não encontrado!");
        logger.info("Criando um medico!");
        var entity =  medicoRepository.save(Medico);
        return entity;
    }

    public Medico update(Medico medico, Long id) {
        logger.info("Atualizando um medico!");

        findById(id);

        return medicoRepository.save(medico);
    }

    public void delete(Long id) {

        logger.info("Deletando um medico!");

        var entity = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico não encontrado!"));
        medicoRepository.delete(entity);
    }
}



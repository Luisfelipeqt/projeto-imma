package br.com.projectcedro.backend.projectcedro.api.medicos.services;

import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.exceptions.ModelNotFoundException;
import br.com.projectcedro.backend.projectcedro.core.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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



    public Optional<Medico> findById(String id) {
        logger.info("Encontrando um medico!");
        var entity = medicoRepository.findById(id);
        return entity;
    }

    public Medico create(Medico Medico) {
        if (Medico == null) throw new ModelNotFoundException("Medico não encontrado!");
        var entity =  medicoRepository.save(Medico);
        logger.info("Criando um medico!");

        return entity;
    }

    public Medico update(String id, Medico medico) {
        try {
            Medico buscarMedico = medicoRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Médico não encontrado!"));

            buscarMedico.setFirstName(medico.getFirstName());
            buscarMedico.setLastName(medico.getLastName());
            buscarMedico.setCpf(medico.getCpf());
            buscarMedico.setCrm(medico.getCrm());
            buscarMedico.setTelefone(medico.getTelefone());
            buscarMedico.setEmail(medico.getEmail());
            buscarMedico.setAtivo(medico.getAtivo());
            logger.info("Atualizando um medico!");
            return medicoRepository.save(buscarMedico);
        }
        catch (EntityNotFoundException e){
            throw new ModelNotFoundException(e.getMessage());
        }
    }


    public void delete(String id) {
        logger.info("Deletando um medico!");
        var entity = medicoRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Medico não encontrado!"));
        medicoRepository.delete(entity);
    }

    @Override
    public Optional<Medico> findMedicosByCpf(String cpf) {
        return medicoRepository.findMedicoByCpf(cpf);
    }
}



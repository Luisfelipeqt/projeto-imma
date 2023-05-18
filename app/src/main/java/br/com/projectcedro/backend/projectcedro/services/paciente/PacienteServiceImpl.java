package br.com.projectcedro.backend.projectcedro.services.paciente;

import br.com.projectcedro.backend.projectcedro.entities.Paciente;
import br.com.projectcedro.backend.projectcedro.repositories.PacienteRepository;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements IPacienteService{

    private final Logger logger = Logger.getLogger(PacienteServiceImpl.class.getName());


    private final PacienteRepository pacienteRepository;


    public List<Paciente> findAll() {
        logger.info("Encontrando todos os pacientes!");
        var entity = pacienteRepository.findAll();
        return entity;
    }

    public Page<Paciente> findAll(Pageable pageable) {
        logger.info("Encontrando todas os pacientes!");
        var entity = pacienteRepository.findAll(pageable);
        return  entity;
    }

    public Paciente findById(Long id) {
        logger.info("Encontrando um paciente!");
        var entity = pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
        return entity;
    }

    public Paciente create(Paciente Paciente) {
        if (Paciente == null) throw new ResourceNotFoundException("Paciente não encontrado!");
        logger.info("Criando um paciente!");
        var entity =  pacienteRepository.save(Paciente);
        return entity;
    }

    public Paciente update(Paciente paciente, Long id) {
        logger.info("Atualizando um paciente!");

        findById(id);

        return pacienteRepository.save(paciente);
    }

    public void delete(Long id) {

        logger.info("Deletando um paciente!");

        var entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!"));
        pacienteRepository.delete(entity);
    }

    @Override
    public boolean existsPacienteByCpf(Paciente paciente) {
        return true;
    }

    @Override
    public boolean existsPacienteByCpf(String pacienteCpf) {
        return true;
    }

    @Override
    public Optional<Paciente> findByCpf(String cpf) {
        return Optional.empty();
    }
}

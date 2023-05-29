package br.com.projectcedro.backend.projectcedro.api.pacientes.services;

import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import br.com.projectcedro.backend.projectcedro.core.exceptions.ModelNotFoundException;
import br.com.projectcedro.backend.projectcedro.core.exceptions.PacienteNotFoundException;
import br.com.projectcedro.backend.projectcedro.core.repositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements IPacienteService {

    private final Logger logger = Logger.getLogger(PacienteServiceImpl.class.getName());


    private final PacienteRepository pacienteRepository;
    private final PasswordEncoder passwordEncoder;



    public List<Paciente> findAll() {
        logger.info("Encontrando todos os pacientes!");
        var entity = pacienteRepository.findAll();
        return entity;
    }

    public Page<Paciente> findAll(Pageable pageable) {
        logger.info("Encontrando todos os pacientes!");
        var entity = pacienteRepository.findAll(pageable);
        return  entity;
    }

    public Optional<Paciente> findById(String id) {
        logger.info("Encontrando um paciente!");
        var entity = pacienteRepository.findById(id);
        return entity;
    }

    public Paciente create(Paciente paciente) {
        if (paciente == null) throw new PacienteNotFoundException("Paciente não encontrado!");
        logger.info("Criando um paciente!");
        paciente.setPassword(passwordEncoder.encode(paciente.getPassword()));
        var entity =  pacienteRepository.save(paciente);
        return entity;
    }

    public Paciente update(String id, Paciente paciente) {
        try {
            Paciente buscarPaciente = pacienteRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Paciente não encontrado!"));

            buscarPaciente.setFirstName(paciente.getFirstName());
            buscarPaciente.setLastName(paciente.getLastName());
            buscarPaciente.setCpf(paciente.getCpf());
            buscarPaciente.setDataNascimento(paciente.getDataNascimento());
            buscarPaciente.setTelefone(paciente.getTelefone());
            buscarPaciente.setEmail(paciente.getEmail());
            buscarPaciente.setPassword(passwordEncoder.encode(paciente.getPassword()));
            logger.info("Atualizando um paciente!");
            return pacienteRepository.save(buscarPaciente);
        } catch (EntityNotFoundException e) {
            throw new ModelNotFoundException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        logger.info("Deletando um paciente!");
        var entity = pacienteRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Paciente não encontrado!"));
        pacienteRepository.delete(entity);
    }
    

    @Override
    public Optional<Paciente> findPacienteByCpf(String cpf) {
        return pacienteRepository.findPacientesByCpf(cpf);
    }


}

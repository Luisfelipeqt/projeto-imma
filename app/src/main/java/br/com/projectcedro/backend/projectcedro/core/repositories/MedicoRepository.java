package br.com.projectcedro.backend.projectcedro.core.repositories;

import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, String> {

    Optional<Medico> findMedicoByCpf(String cpf);
}

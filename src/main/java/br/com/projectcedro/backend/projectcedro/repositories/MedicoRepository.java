package br.com.projectcedro.backend.projectcedro.repositories;

import br.com.projectcedro.backend.projectcedro.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}

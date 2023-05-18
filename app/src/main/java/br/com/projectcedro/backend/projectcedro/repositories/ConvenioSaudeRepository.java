package br.com.projectcedro.backend.projectcedro.repositories;

import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.entities.ConvenioSaude;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvenioSaudeRepository extends JpaRepository<ConvenioSaude, Long> {
}

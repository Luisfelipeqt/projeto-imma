package br.com.projectcedro.backend.projectcedro.core.repositories;

import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, String> {



    Consulta findConsultaByData(LocalDateTime data);

    @Query("select c from Consulta c where c.medico.id = ?1")
    Consulta findConsultaByMedicoId(Long medicoId);


    Optional<Object> findByData(LocalDateTime data);

    boolean existsByMedicoAndData(Medico medico, LocalDateTime data);

}
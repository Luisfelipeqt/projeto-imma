package br.com.projectcedro.backend.projectcedro.repositories;

import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {



    Consulta findConsultaByData(LocalDateTime data);

    @Query("select c from Consulta c where c.medico.id = ?1")
    Consulta findConsultaByMedicoId(Long medicoId);


    Optional<Object> findByData(LocalDateTime data);

    boolean existsByMedicoAndData(Medico medico, LocalDateTime data);

}
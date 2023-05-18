package br.com.projectcedro.backend.projectcedro.repositories;

import br.com.projectcedro.backend.projectcedro.entities.Medico;
import br.com.projectcedro.backend.projectcedro.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    Optional<Paciente> findByEmail(String email);

    @Query("select p from Paciente p where p.cpf = ?1")
    Optional<Paciente> findByCpf(Paciente cpf);

    String findPacientesByCpf(String paciente);
    boolean existsPacienteByCpf(String paciente);
}

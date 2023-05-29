package br.com.projectcedro.backend.projectcedro.core.repositories;

import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, String>{
    @Query("select p from Paciente p where p.cpf = ?1")
    Optional<Paciente> findByCpf(Paciente cpf);

    Optional<Paciente> existsPacienteByCpf(String cpf);


    Optional<Paciente> findByEmail(String email);
    Optional<Paciente> findPacientesByCpf(String cpf);
}

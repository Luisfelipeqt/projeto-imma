package br.com.projectcedro.backend.projectcedro.repositories;

import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}

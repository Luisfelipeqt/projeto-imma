package br.com.projectcedro.backend.projectcedro.services.usuario;

import br.com.projectcedro.backend.projectcedro.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuariosService {

     List<Usuarios> findAll();

     Page<Usuarios> findAll(Pageable pageable);

     Usuarios findById(Long id);

     Usuarios create(Usuarios Usuarios);

     Usuarios update(Usuarios usuarios, Long id);

     void delete(Long id);
}


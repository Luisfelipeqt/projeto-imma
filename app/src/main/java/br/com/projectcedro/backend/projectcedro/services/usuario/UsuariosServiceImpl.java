package br.com.projectcedro.backend.projectcedro.services.usuario;

import br.com.projectcedro.backend.projectcedro.entities.Usuarios;
import br.com.projectcedro.backend.projectcedro.repositories.UsuariosRepository;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements IUsuariosService {

    private final Logger logger = Logger.getLogger(UsuariosServiceImpl.class.getName());


    private final UsuariosRepository usuariosRepository;


    public List<Usuarios> findAll() {
        logger.info("Encontrando todos os usuarioss!");
        var entity = usuariosRepository.findAll();
        return entity;
    }

    public Page<Usuarios> findAll(Pageable pageable) {
        logger.info("Encontrando todas os usuarioss!");
        var entity = usuariosRepository.findAll(pageable);
        return  entity;
    }

    public Usuarios findById(Long id) {
        logger.info("Encontrando um usuarios!");
        var entity = usuariosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuarios não encontrado!"));
        return entity;
    }

    public Usuarios create(Usuarios usuarios) {
        if (usuarios == null) throw new ResourceNotFoundException("Usuarios não encontrado!");
        logger.info("Criando um usuarios!");
        var entity =  usuariosRepository.save(usuarios);
        return entity;
    }

    public Usuarios update(Usuarios usuarios, Long id) {
        logger.info("Atualizando um usuarios!");

        findById(id);

        return usuariosRepository.save(usuarios);
    }

    public void delete(Long id) {

        logger.info("Deletando um usuarios!");

        var entity = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuarios não encontrado!"));
        usuariosRepository.delete(entity);
    }
}



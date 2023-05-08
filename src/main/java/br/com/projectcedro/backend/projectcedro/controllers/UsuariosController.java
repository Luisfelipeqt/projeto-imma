package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.config.security.AuthToken;
import br.com.projectcedro.backend.projectcedro.config.security.TokenUtil;
import br.com.projectcedro.backend.projectcedro.entities.Usuarios;
import br.com.projectcedro.backend.projectcedro.repositories.UsuariosRepository;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
import br.com.projectcedro.backend.projectcedro.uteis.SenhaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/v1")
public class UsuariosController {

    private final UsuariosRepository usuariosRepository;


    @RequestMapping(method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    public ResponseEntity<AuthToken> realizarLogin(@RequestBody Usuarios usuarios){
        if(usuarios.getLogin().equals("felipe") && usuarios.getSenha().equals("123456789")){
            usuariosRepository.save(usuarios);
            return ResponseEntity.ok(TokenUtil.encodeToken(usuarios));
        }
        return ResponseEntity.status(403).build();
    }
}

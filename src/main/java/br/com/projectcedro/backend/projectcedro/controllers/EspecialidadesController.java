package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.entities.EspecialidadeMedica;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/especialidades/v1")
public class EspecialidadesController {

    private final Logger logger = Logger.getLogger(EspecialidadesController.class.getName());

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    public EspecialidadeMedica[] getEspecialidades() {
        logger.info("Solicitada lista de especialidades médicas.");
        return EspecialidadeMedica.values();
    }
}

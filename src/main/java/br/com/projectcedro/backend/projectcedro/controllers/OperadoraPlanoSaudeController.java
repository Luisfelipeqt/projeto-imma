package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.entities.PlanosDeSaude;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/planos/v1")
public class OperadoraPlanoSaudeController {

    private final Logger logger = Logger.getLogger(OperadoraPlanoSaudeController.class.getName());

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    public PlanosDeSaude[] getConvenios() {
        logger.info("Solicitada lista de convenios médicas.");
        return PlanosDeSaude.values();
    }
}

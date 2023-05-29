package br.com.projectcedro.backend.projectcedro.api.pacientes.controllers;

import br.com.projectcedro.backend.projectcedro.core.entities.PlanosDeSaude;
import br.com.projectcedro.backend.projectcedro.config.uteis.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/planos/v1")
@Tag(name = "Planos", description = "Endpoints para Gerenciamento de Planos")
public class OperadoraPlanoSaudeController {

    private final Logger logger = Logger.getLogger(OperadoraPlanoSaudeController.class.getName());

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a busca por todoos os planos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public PlanosDeSaude[] getConvenios() {
        logger.info("Solicitada lista de convenios médicas.");
        return PlanosDeSaude.values();
    }
}

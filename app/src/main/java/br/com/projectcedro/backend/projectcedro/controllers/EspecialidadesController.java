package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.entities.EspecialidadeMedica;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/especialidades/v1")
@Tag(name = "Especialidades", description = "Endpoints para Gerenciamento de Especialidades Médicas")
public class EspecialidadesController {

    private final Logger logger = Logger.getLogger(EspecialidadesController.class.getName());

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a busca por todas as especialidades", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public EspecialidadeMedica[] getEspecialidades() {
        logger.info("Solicitada lista de especialidades médicas.");
        return EspecialidadeMedica.values();
    }
}

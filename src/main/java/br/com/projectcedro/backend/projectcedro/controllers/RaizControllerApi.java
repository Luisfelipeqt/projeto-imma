package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.hateoas.RaizModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("http://localhost:8080/")
@Tag(name = "API", description = "Endpoints para Gerenciamento de API's")
public class RaizControllerApi {

    @GetMapping
    @Operation(summary = "Realiza a busca pelas API's", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public RaizModel raizConsulta(){
        RaizModel raizModel = new RaizModel();

        Link consultas = linkTo(methodOn(ConsultaController.class).findAll(null))
                .withRel("consultas")
                .withType("GET");


        Link medicos = linkTo(methodOn(MedicoController.class).findAll(null))
                .withRel("medicos")
                .withType("GET");


        Link pacientes = linkTo(methodOn(PacienteController.class).findAll(null))
                .withRel("pacientes")
                .withType("GET");


        raizModel.add(consultas, medicos, pacientes);

        return raizModel;
    }
}

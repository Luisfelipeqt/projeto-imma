package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.entities.Paciente;
import br.com.projectcedro.backend.projectcedro.hateoas.PacienteAssembler;
import br.com.projectcedro.backend.projectcedro.services.paciente.IPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pacientes/v1")
@Tag(name = "Pacientes", description = "Endpoints para Gerenciamento de Pacientes")
public class PacienteController {

     
    private final IPacienteService pacienteService;

     
    private final PacienteAssembler pacienteAssembler;

     
    private final PagedResourcesAssembler<Paciente> pagedResourcesAssembler;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Encontrando todos os pacientes", description = "Encontrando todos os pacientes", tags = {"Pacientes"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Paciente.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })    public CollectionModel<EntityModel<Paciente>> findAll(Pageable pageable){
        Page<Paciente> pacientes = pacienteService.findAll(pageable);
        return pagedResourcesAssembler.toModel(pacientes, pacienteAssembler);
    }

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Encontrando todos os pacientes", description = "Encontrando todos os pacientes", tags = {"Pacientes"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Paciente.class))
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })    public Paciente findById(@PathVariable(value = "id") Long id) {
        Link selfLink = linkTo(methodOn(PacienteController.class).findAll(null))
                .withRel("Lista de Pacientes")
                .withType("GET");
        Paciente paciente =  pacienteService.findById(id);
        return paciente;

    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Adicionando um novo paciente",
            description = "Adiciona um novo paciente passando uma representação do paciente em JSON, XML ou YML",
            tags = {"Medicos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Paciente.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Paciente> create(@RequestBody Paciente paciente) {

        Paciente consults = pacienteService.create(paciente);
        return pacienteAssembler.toModel(consults);
    }

    @PutMapping(value = "/{id}",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Atualizando um paciente",
            description = "Atualizando um paciente passando uma representação da paciente em JSON, XML ou YML.",
            tags = {"Medicos"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Paciente.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })    public EntityModel<Paciente> update(@RequestBody Paciente paciente, @PathVariable Long id) {
        Paciente consults = pacienteService.update(paciente, id);
        return pacienteAssembler.toModel(consults);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletando um paciente",
            description = "Deleta um paciente passando uma representação da paciente em JSON, XML ou YML.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
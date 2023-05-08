package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.entities.Medico;
import br.com.projectcedro.backend.projectcedro.hateoas.MedicoAssembler;
import br.com.projectcedro.backend.projectcedro.services.medico.IMedicoService;
import br.com.projectcedro.backend.projectcedro.uteis.MediaType;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/medicos/v1")
@Tag(name = "Medicos", description = "Endpoints para Gerenciamento de Medicos")
public class MedicoController {

    private final IMedicoService medicoService;

    private final MedicoAssembler medicoAssembler;

    private final PagedResourcesAssembler<Medico> pagedResourcesAssembler;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Encontrando todos os médicos", description = "Encontrando todos os médico", tags = {"Medicos"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Medico.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })
    public CollectionModel<EntityModel<Medico>> findAll(Pageable pageable){
        Page<Medico> medicos = medicoService.findAll(pageable);
        return pagedResourcesAssembler.toModel(medicos, medicoAssembler);
    }

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Encontrando todos os medicos", description = "Encontrando todos os medicos", tags = {"Medicos"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Medico.class))
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })
    public Medico findById(@PathVariable(value = "id") Long id) {
        Link selfLink = linkTo(methodOn(MedicoController.class).findAll(null))
                .withRel("Lista de Medicos")
                .withType("GET");
        Medico medico =  medicoService.findById(id);
        medico.add(selfLink);
        return medico;

    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Adicionando um novo médico",
            description = "Adiciona um novo médico passando uma representação do médico em JSON, XML ou YML",
            tags = {"Medicos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Medico.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Medico> create(@RequestBody Medico medico) {

        Medico consults = medicoService.create(medico);
        return medicoAssembler.toModel(consults);
    }

    @PutMapping(value = "/{id}",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Atualizando uma médico",
            description = "Atualizando um médico passando uma representação da pessoa em JSON, XML ou YML.",
            tags = {"Medicos"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Medico.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public EntityModel<Medico> update(@RequestBody Medico medico, @PathVariable Long id) {
        Medico consults = medicoService.update(medico, id);
        return medicoAssembler.toModel(consults);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletando um médico",
            description = "Deleta um médico passando uma representação do médico em JSON, XML ou YML.",
            tags = {"Medicos"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        medicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.config.JacksonConfig;
import br.com.projectcedro.backend.projectcedro.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.hateoas.ConsultaAssembler;
import br.com.projectcedro.backend.projectcedro.services.consulta.IConsultaService;
import br.com.projectcedro.backend.projectcedro.services.medico.IMedicoService;
import br.com.projectcedro.backend.projectcedro.services.paciente.IPacienteService;
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
@RequestMapping("/api/consultas/v1")
@Tag(name = "Consultas", description = "Endpoints para Gerenciamento de Consultas")
public class ConsultaController {

    private final IConsultaService consultaService;

    private final IMedicoService medicoService;

    private final IPacienteService pacienteService;

    private final ConsultaAssembler consultaAssembler;

    private final JacksonConfig jacksonConfig;

    private final PagedResourcesAssembler<Consulta> pagedResourcesAssembler;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Encontrando todos os médicos", description = "Encontrando todos os médico", tags = {"Consultas"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Consulta.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })
    public CollectionModel<EntityModel<Consulta>> findAll(Pageable pageable){
        Page<Consulta> consultas = consultaService.findAll(pageable);
        return pagedResourcesAssembler.toModel(consultas, consultaAssembler);
    }

    @GetMapping(value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Encontrando todos os consultas", description = "Encontrando todos os consultas", tags = {"Consultas"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Consulta.class))
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

    })
    public Consulta findById(@PathVariable(value = "id") Long id) {
        Link selfLink = linkTo(methodOn(ConsultaController.class).findAll(null))
                .withRel("Lista de Consultas")
                .withType("GET");
        Consulta consulta =  consultaService.findById(id);
        consulta.add(selfLink);
        return consulta;

    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Adicionando um novo médico",
            description = "Adiciona um novo médico passando uma representação do médico em JSON, XML ou YML",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Consulta.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Consulta consulta) {
        var medico = medicoService.findById(consulta.getMedico().getId());
        var dataHora = consulta.getData();
        var paciente = pacienteService.findById(consulta.getPaciente().getId());
        if (consultaService.existeConsultaAgendada(medico, dataHora)) {
            return ResponseEntity.badRequest().body("Já existe uma consulta agendada para este médico nesta data e hora");
        }
        Consulta criandoConsulta = consultaService.create(consulta);
        criandoConsulta.setMedico(medico);
        criandoConsulta.setPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(criandoConsulta);
    }


    @PutMapping(value = "/{id}",
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Atualizando uma médico",
            description = "Atualizando um médico passando uma representação da pessoa em JSON, XML ou YML.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200", content = {
                            @Content(schema = @Schema(implementation = Consulta.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public EntityModel<Consulta> update(@RequestBody Consulta consulta, @PathVariable Long id) {
        Consulta consults = consultaService.update(consulta, id);
        return consultaAssembler.toModel(consults);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletando um médico",
            description = "Deleta um médico passando uma representação do médico em JSON, XML ou YML.",
            tags = {"Consultas"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

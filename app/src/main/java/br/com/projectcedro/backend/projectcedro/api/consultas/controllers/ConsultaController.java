package br.com.projectcedro.backend.projectcedro.api.consultas.controllers;

import br.com.projectcedro.backend.projectcedro.api.common.dtos.ErrorResponse;
import br.com.projectcedro.backend.projectcedro.api.common.routes.ApiRoutes;
import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaRequest;
import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaResponse;
import br.com.projectcedro.backend.projectcedro.api.consultas.services.IConsultaService;
import br.com.projectcedro.backend.projectcedro.api.medicos.services.IMedicoService;
import br.com.projectcedro.backend.projectcedro.api.pacientes.services.IPacienteService;
import br.com.projectcedro.backend.projectcedro.config.hateoas.ConsultaAssembler;
import br.com.projectcedro.backend.projectcedro.config.uteis.EmailSenderService;
import br.com.projectcedro.backend.projectcedro.config.uteis.MediaType;
import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static br.com.projectcedro.backend.projectcedro.api.consultas.mappers.ConsultaMapper.MAPPER;


@RestController
@RequiredArgsConstructor
@Tag(name = "Consultas", description = "Endpoints para Gerenciamento de Consultas")
public class ConsultaController {

    private final IConsultaService consultaService;
    private final IMedicoService medicoService;
    private final IPacienteService pacienteService;
    private final ConsultaAssembler consultaAssembler;
    private final EmailSenderService emailSenderService;


    @GetMapping(value = ApiRoutes.BUSCAR_CONSULTAS,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Realiza a busca por todas as consultas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<ConsultaResponse>> findAll(){
        List<Consulta> consultas = consultaService.findAll();
        List<ConsultaResponse> consultaResponse = MAPPER.toConsultaReponseList(consultas);
        return ResponseEntity.status(HttpStatus.OK).body(consultaResponse);
    }

    @GetMapping(value = ApiRoutes.BUSCAR_CONSULTAS_ID,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Realiza a busca por uma consulta com o ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> findById(@PathVariable(value = "id") String id) {
        var buscarPorId = consultaService.findById(id);
        if (buscarPorId.isPresent()) {
            var pacienteResponse = MAPPER.toConsultaResponse(buscarPorId.get());
            return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = ApiRoutes.CADASTRAR_CONSULTAS,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }
    )
    @Operation(summary = "Realiza a criação de uma consulta", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> create(@Valid @RequestBody ConsultaRequest consulta) {
        var medico = medicoService.findById(consulta.getMedico().getId());
        var paciente = pacienteService.findById(consulta.getPaciente().getId());
        var dataHora = consulta.getData();
        if (consultaService.existeConsultaAgendada(medico, dataHora)) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "Já existe uma consulta para esse horário", LocalDateTime.now()));
        }
        Consulta criandoConsulta = consultaService.create(MAPPER.toConsultaEntity(consulta));
        criandoConsulta.setMedico(medico.get());
        criandoConsulta.setPaciente(paciente.get());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = dataHora.format(formatter);

        Thread emailThread = new Thread(() -> {
            String destinatario = criandoConsulta.getPaciente().getEmail();
            String assunto = "Agendamento de consulta";
            String conteudo = "Olá " + criandoConsulta.getPaciente().getFirstName()
                    + " "
                    + criandoConsulta.getPaciente().getLastName()
                    + ",\n\n Sua consulta foi agendada para o dia "
                    + dataFormatada
                    + " e o seu médico é o DR."
                    + criandoConsulta.getMedico().getFirstName()
                    + " "
                    + criandoConsulta.getMedico().getLastName();

            emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);
        });
        emailThread.start(); // Inicia a thread de envio de email
        return ResponseEntity.status(HttpStatus.CREATED).body(criandoConsulta);
    }


    @PutMapping(value = ApiRoutes.ATUALIZAR_CONSULTAS,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Realiza a atualização de uma consulta com o ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody @Valid ConsultaRequest consultaRequest) {
        var buscarConsulta = MAPPER.toConsultaEntity(consultaRequest);
        Consulta atualizarConsulta = consultaService.update(id, buscarConsulta);
        var consultaResponse = MAPPER.toConsultaResponse(atualizarConsulta);
        Thread emailThread = new Thread(() -> {
            String destinatario = consultaRequest.getPaciente().getEmail();
            String assunto = "Dados atualizado com sucesso";
            String conteudo = "Olá "
                    + consultaRequest.getPaciente().getFirstName()
                    + " "
                    + consultaRequest.getPaciente().getLastName()
                    + ",\n\nSua consulta foi atualizada com sucesso.";
            emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);
        });
        emailThread.start();
        return ResponseEntity.status(HttpStatus.OK).body(MAPPER.toConsultaResponse(atualizarConsulta));
    }


    @DeleteMapping(value = ApiRoutes.DELETAR_CONSULTAS)
    @Operation(summary = "Realiza a exclusão de uma consulta", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        var buscarConsulta = consultaService.findById(id);
        if(buscarConsulta.isPresent()){
            consultaService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

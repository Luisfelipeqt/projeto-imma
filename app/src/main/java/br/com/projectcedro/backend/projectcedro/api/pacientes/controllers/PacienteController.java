package br.com.projectcedro.backend.projectcedro.api.pacientes.controllers;

import br.com.projectcedro.backend.projectcedro.api.common.dtos.ErrorResponse;
import br.com.projectcedro.backend.projectcedro.api.common.routes.ApiRoutes;
import br.com.projectcedro.backend.projectcedro.api.consultas.mappers.ConsultaMapper;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.api.medicos.mappers.MedicoMapper;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteRequest;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteResponse;
import br.com.projectcedro.backend.projectcedro.api.pacientes.services.IPacienteService;
import br.com.projectcedro.backend.projectcedro.config.uteis.EmailSenderService;
import br.com.projectcedro.backend.projectcedro.config.uteis.MediaType;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.projectcedro.backend.projectcedro.api.pacientes.mappers.PacienteMapper.MAPPER;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pacientes", description = "Endpoints para Gerenciamento de Pacientes")
public class PacienteController {


    private final IPacienteService pacienteService;
    private final EmailSenderService emailSenderService;


    @GetMapping(value = ApiRoutes.BUSCAR_PACIENTES,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Realiza a busca por todos os pacientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<PacienteResponse>> findAll(){
        List<Paciente> pacientes = pacienteService.findAll();
        List<PacienteResponse> pacienteResponse = MAPPER.toPacienteReponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @GetMapping(value = ApiRoutes.BUSCAR_PACIENTES_ID,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a busca por um paciente pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<PacienteResponse> findById(@PathVariable(value = "id") String id) {
        var buscarPorId = pacienteService.findById(id);
        if (buscarPorId.isPresent()) {
            var pacienteResponse = MAPPER.toPacienteResponse(buscarPorId.get());
            return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = ApiRoutes.CADASTRAR_PACIENTES,
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a criação de um paciente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> create(@Valid @RequestBody PacienteRequest paciente) {
        var buscarPorCpf = pacienteService.findPacienteByCpf(paciente.getCpf());
        if(!buscarPorCpf.isPresent()){
            var criarPaciente = pacienteService.create(MAPPER.toPacienteEntity(paciente));
            var pacienteResponse = MAPPER.toPacienteResponse(criarPaciente);
            Thread emailThread = new Thread(() -> {
                String destinatario = paciente.getEmail();
                String assunto = "Cadastro efetuado com sucesso";
                String conteudo = "Olá " + paciente.getFirstName() + " " + paciente.getLastName() + ",\n\nBem-vindo ao nosso aplicativo!";
                emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);
            });
            emailThread.start();
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409, "CPF já cadastrado!", LocalDateTime.now()));
    }

    @PutMapping(value = ApiRoutes.ATUALIZAR_PACIENTES,
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a atualização de um paciente pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody PacienteRequest pacienteRequest) {
        var buscarCpfPaciente = pacienteService.findPacienteByCpf(pacienteRequest.getCpf());
        if(!buscarCpfPaciente.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "CPF incorreto", LocalDateTime.now()));
        }
        var pacienteEntity = MAPPER.toPacienteEntity(pacienteRequest);
        var buscarPorId = pacienteService.findById(id);
        if (buscarPorId.isPresent()) {
            var atualizarPaciente = pacienteService.update(id, pacienteEntity);
            var pacienteResponse = MAPPER.toPacienteResponse(atualizarPaciente);

            // Cria uma nova thread para enviar o email
            Thread emailThread = new Thread(() -> {
                String destinatario = pacienteRequest.getEmail();
                String assunto = "Dados atualizados com sucesso";
                String conteudo = "Olá " + pacienteRequest.getFirstName() + " " + pacienteRequest.getLastName() + ",\n\nSeus dados foram atualizados com sucesso!!";
                emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);
            });

            emailThread.start(); // Inicia a thread de envio de email
            return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse(400, "Paciente não encontrado", LocalDateTime.now()));
    }



    @DeleteMapping(value = ApiRoutes.DELETAR_PACIENTES)
    @Operation(summary = "Realiza a exclusão de um paciente pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        var buscarPaciente = pacienteService.findById(id);
        if(buscarPaciente.isPresent()){
            pacienteService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
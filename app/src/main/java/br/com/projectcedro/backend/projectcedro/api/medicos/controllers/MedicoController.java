package br.com.projectcedro.backend.projectcedro.api.medicos.controllers;

import br.com.projectcedro.backend.projectcedro.api.common.dtos.ErrorResponse;
import br.com.projectcedro.backend.projectcedro.api.common.routes.ApiRoutes;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.api.medicos.services.IMedicoService;
import br.com.projectcedro.backend.projectcedro.config.uteis.EmailSenderService;
import br.com.projectcedro.backend.projectcedro.config.uteis.MediaType;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.projectcedro.backend.projectcedro.api.medicos.mappers.MedicoMapper.MAPPER;


@RestController
@RequiredArgsConstructor
@Tag(name = "Medicos", description = "Endpoints para Gerenciamento de Medicos")
public class MedicoController {

    private final IMedicoService medicoService;
    private final EmailSenderService emailSenderService;


    @GetMapping(value = ApiRoutes.BUSCAR_MEDICOS,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(summary = "Realiza a busca por todos os médicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<MedicoResponse>> findAll() {
        List<Medico> medicos = medicoService.findAll();
        List<MedicoResponse> medicosResponse = MAPPER.toMedicoReponseList(medicos);
        return ResponseEntity.ok().body(medicosResponse);
    }

    @GetMapping(value = ApiRoutes.BUSCAR_MEDICOS_ID,
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a busca por um médico pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<MedicoResponse> findById(@PathVariable(value = "id") String id) {
        var buscarPorId = medicoService.findById(id);
        if (buscarPorId.isPresent()) {
            MedicoResponse medicoResponse = MAPPER.toMedicoResponse(buscarPorId.get());
            return ResponseEntity.status(HttpStatus.OK).body(medicoResponse);
        }
        return ResponseEntity.badRequest().build();
    }



    @PostMapping(value = ApiRoutes.CADASTRAR_MEDICOS,
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a criação de um médico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> create(@Valid @RequestBody MedicoRequest medico) {
        var buscarPorCpf = medicoService.findMedicosByCpf(medico.getCpf());
        if(!buscarPorCpf.isPresent()){
            var criarPaciente = medicoService.create(MAPPER.toMedicoEntity(medico));

            Thread emailThread = new Thread(() -> {
                String destinatario = medico.getEmail();
                String assunto = "Cadastro efetuado com sucesso";
                String conteudo = "Olá " + medico.getFirstName() + " " + medico.getLastName() + ",\n\nBem-vindo ao nosso aplicativo!";
                emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);
            });
            emailThread.start(); // Inicia a thread de envio de email

            return ResponseEntity.status(HttpStatus.CREATED).body(criarPaciente);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(409, "CPF já cadastrado!", LocalDateTime.now()));
    }

    @PutMapping(value = ApiRoutes.ATUALIZAR_MEDICOS,
            consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  },
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML  })
    @Operation(summary = "Realiza a atualização de um médico pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> update(@PathVariable(value = "id") String id, @RequestBody MedicoRequest medico) {
        var buscarCpfMedico = medicoService.findMedicosByCpf(medico.getCpf());
        if(!buscarCpfMedico.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(400, "CPF incorreto", LocalDateTime.now()));
        }
        var medicoEntity = MAPPER.toMedicoEntity(medico);
        var buscarPorId = medicoService.findById(id);
        if (buscarPorId.isPresent()) {
            Medico atualizarMedico = medicoService.update(id, medicoEntity);
            var medicoResponse = MAPPER.toMedicoResponse(atualizarMedico);

            // Cria uma nova thread para enviar o email
            Thread emailThread = new Thread(() -> {
                String destinatario = medico.getEmail();
                String assunto = "Dados atualizados com sucesso";
                String conteudo = "Olá " + medico.getFirstName() + " " + medico.getLastName() + ",\n\nSeus dados foram atualizados com sucesso!!";
                emailSenderService.sendSimpleEmail(destinatario, assunto, conteudo);

            });
            emailThread.start(); // Inicia a thread de envio de email
            return ResponseEntity.status(HttpStatus.OK).body(medicoResponse);
        }
        return ResponseEntity.badRequest().body(new ErrorResponse(400, "Médico não encontrado", LocalDateTime.now()));
    }

    @DeleteMapping(value = ApiRoutes.DELETAR_MEDICOS)
    @Operation(summary = "Realiza a exclusão de um médico pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
            @ApiResponse(description = "Não encontrado", responseCode = "404", content = @Content),
            @ApiResponse(description = "Error no servidor", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        var buscarMedico = medicoService.findById(id);
        if(buscarMedico.isPresent()){
            medicoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

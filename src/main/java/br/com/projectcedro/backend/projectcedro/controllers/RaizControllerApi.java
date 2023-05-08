package br.com.projectcedro.backend.projectcedro.controllers;

import br.com.projectcedro.backend.projectcedro.hateoas.RaizModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("http://localhost:8080/")
public class RaizControllerApi {

    @GetMapping
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

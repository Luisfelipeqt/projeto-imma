package br.com.projectcedro.backend.projectcedro.hateoas;

import br.com.projectcedro.backend.projectcedro.controllers.PacienteController;
import br.com.projectcedro.backend.projectcedro.entities.Paciente;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PacienteAssembler implements SimpleRepresentationModelAssembler<Paciente> {


    @Override
    public void addLinks(EntityModel<Paciente> resource) {
        Long id = resource.getContent().getId();
        Link selfLink = linkTo(methodOn(PacienteController.class).findById(id))
                .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(PacienteController.class).update(null, id))
                .withSelfRel()
                .withType("PUT");

        Link excluirLink = linkTo(methodOn(PacienteController.class).delete(id))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Paciente>> resources) {
        Link cadastroLink = linkTo(methodOn(PacienteController.class).create(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(PacienteController.class).findAll(null))
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);

    }
}

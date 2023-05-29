package br.com.projectcedro.backend.projectcedro.config.hateoas;

import br.com.projectcedro.backend.projectcedro.api.medicos.controllers.MedicoController;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicoAssembler implements SimpleRepresentationModelAssembler<Medico> {


    @Override
    public void addLinks(EntityModel<Medico> resource) {
        String id = resource.getContent().getId();
        Link selfLink = linkTo(methodOn(MedicoController.class).findById(String.valueOf(id)))
                .withSelfRel()
                .withType("GET");

        Link criarLink = linkTo(methodOn(MedicoController.class).create(new MedicoRequest()))
                .withSelfRel()
                .withType("POST");

        Link editarLink = linkTo(methodOn(MedicoController.class).update(id, new MedicoRequest()))
                .withSelfRel()
                .withType("PUT");

        Link excluirLink = linkTo(methodOn(MedicoController.class).delete(id))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, criarLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Medico>> resources) {
        Link cadastroLink = linkTo(methodOn(MedicoController.class).create(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(MedicoController.class).findAll())
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);

    }
}

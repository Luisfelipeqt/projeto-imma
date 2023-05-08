package br.com.projectcedro.backend.projectcedro.hateoas;

import br.com.projectcedro.backend.projectcedro.controllers.MedicoController;
import br.com.projectcedro.backend.projectcedro.entities.Medico;
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
        Long id = resource.getContent().getId();
        Link selfLink = linkTo(methodOn(MedicoController.class).findById(id))
                .withSelfRel()
                .withType("GET");

        Link editarLink = linkTo(methodOn(MedicoController.class).update(null, id))
                .withSelfRel()
                .withType("PUT");

        Link excluirLink = linkTo(methodOn(MedicoController.class).delete(id))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Medico>> resources) {
        Link cadastroLink = linkTo(methodOn(MedicoController.class).create(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(MedicoController.class).findAll(null))
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);

    }
}

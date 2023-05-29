package br.com.projectcedro.backend.projectcedro.config.hateoas;

import br.com.projectcedro.backend.projectcedro.api.consultas.controllers.ConsultaController;
import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaRequest;
import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ConsultaAssembler implements SimpleRepresentationModelAssembler<Consulta> {


    @Override
    public void addLinks(EntityModel<Consulta> resource) {
        String id = resource.getContent().getId();
        Link selfLink = linkTo(methodOn(ConsultaController.class).findById(String.valueOf(id)))
                .withSelfRel()
                .withType("GET");

        Link criarLink = linkTo(methodOn(ConsultaController.class).create(new ConsultaRequest()))
                .withSelfRel()
                .withType("POST");

        Link editarLink = linkTo(methodOn(ConsultaController.class).update(id, new ConsultaRequest()))
                .withSelfRel()
                .withType("PUT");

        Link excluirLink = linkTo(methodOn(ConsultaController.class).delete(id))
                .withSelfRel()
                .withType("DELETE");

        resource.add(selfLink, criarLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Consulta>> resources) {
        Link cadastroLink = linkTo(methodOn(ConsultaController.class).create(null))
                .withSelfRel()
                .withType("POST");

        Link selfLink = linkTo(methodOn(ConsultaController.class).findAll())
                .withSelfRel()
                .withType("GET");

        resources.add(selfLink, cadastroLink);

    }
}

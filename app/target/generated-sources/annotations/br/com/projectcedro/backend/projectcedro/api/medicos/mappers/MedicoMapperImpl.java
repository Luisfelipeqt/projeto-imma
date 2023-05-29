package br.com.projectcedro.backend.projectcedro.api.medicos.mappers;

import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-28T17:00:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MedicoMapperImpl implements MedicoMapper {

    @Override
    public MedicoRequest toMedicoRequest(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        MedicoRequest medicoRequest = new MedicoRequest();

        medicoRequest.setFirstName( medico.getFirstName() );
        medicoRequest.setLastName( medico.getLastName() );
        medicoRequest.setCpf( medico.getCpf() );
        medicoRequest.setCrm( medico.getCrm() );
        medicoRequest.setTelefone( medico.getTelefone() );
        medicoRequest.setEmail( medico.getEmail() );
        medicoRequest.setEspecialidadeMedica( medico.getEspecialidadeMedica() );

        return medicoRequest;
    }

    @Override
    public MedicoResponse toMedicoResponse(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        MedicoResponse medicoResponse = new MedicoResponse();

        medicoResponse.setId( medico.getId() );
        medicoResponse.setFirstName( medico.getFirstName() );
        medicoResponse.setLastName( medico.getLastName() );
        medicoResponse.setTelefone( medico.getTelefone() );
        medicoResponse.setEmail( medico.getEmail() );
        medicoResponse.setAtivo( medico.getAtivo() );
        medicoResponse.setCreatedAt( medico.getCreatedAt() );
        medicoResponse.setUpdatedAt( medico.getUpdatedAt() );

        return medicoResponse;
    }

    @Override
    public Medico toMedicoReponse(Optional<Medico> medico) {
        if ( medico == null ) {
            return null;
        }

        Medico medico1 = new Medico();

        return medico1;
    }

    @Override
    public Medico toMedicoEntity(MedicoRequest pacienteRequest) {
        if ( pacienteRequest == null ) {
            return null;
        }

        Medico medico = new Medico();

        medico.setFirstName( pacienteRequest.getFirstName() );
        medico.setLastName( pacienteRequest.getLastName() );
        medico.setCpf( pacienteRequest.getCpf() );
        medico.setCrm( pacienteRequest.getCrm() );
        medico.setTelefone( pacienteRequest.getTelefone() );
        medico.setEmail( pacienteRequest.getEmail() );
        medico.setEspecialidadeMedica( pacienteRequest.getEspecialidadeMedica() );

        return medico;
    }

    @Override
    public Medico toMedicoEntity(String id, MedicoRequest pacienteRequest) {
        if ( id == null && pacienteRequest == null ) {
            return null;
        }

        Medico medico = new Medico();

        if ( pacienteRequest != null ) {
            medico.setFirstName( pacienteRequest.getFirstName() );
            medico.setLastName( pacienteRequest.getLastName() );
            medico.setCpf( pacienteRequest.getCpf() );
            medico.setCrm( pacienteRequest.getCrm() );
            medico.setTelefone( pacienteRequest.getTelefone() );
            medico.setEmail( pacienteRequest.getEmail() );
            medico.setEspecialidadeMedica( pacienteRequest.getEspecialidadeMedica() );
        }
        medico.setId( id );

        return medico;
    }

    @Override
    public List<MedicoResponse> toMedicoReponseList(List<Medico> e) {
        if ( e == null ) {
            return null;
        }

        List<MedicoResponse> list = new ArrayList<MedicoResponse>( e.size() );
        for ( Medico medico : e ) {
            list.add( toMedicoResponse( medico ) );
        }

        return list;
    }
}

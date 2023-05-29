package br.com.projectcedro.backend.projectcedro.api.pacientes.mappers;

import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteRequest;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-28T17:00:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public PacienteRequest toPacienteRequest(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteRequest pacienteRequest = new PacienteRequest();

        pacienteRequest.setFirstName( paciente.getFirstName() );
        pacienteRequest.setLastName( paciente.getLastName() );
        pacienteRequest.setCpf( paciente.getCpf() );
        pacienteRequest.setDataNascimento( paciente.getDataNascimento() );
        pacienteRequest.setTelefone( paciente.getTelefone() );
        pacienteRequest.setEmail( paciente.getEmail() );
        pacienteRequest.setPassword( paciente.getPassword() );

        return pacienteRequest;
    }

    @Override
    public PacienteResponse toPacienteResponse(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteResponse pacienteResponse = new PacienteResponse();

        pacienteResponse.setId( paciente.getId() );
        pacienteResponse.setFirstName( paciente.getFirstName() );
        pacienteResponse.setLastName( paciente.getLastName() );
        pacienteResponse.setTelefone( paciente.getTelefone() );
        pacienteResponse.setEmail( paciente.getEmail() );
        pacienteResponse.setCreatedAt( paciente.getCreatedAt() );
        pacienteResponse.setUpdatedAt( paciente.getUpdatedAt() );

        return pacienteResponse;
    }

    @Override
    public Paciente toPacienteReponse(Optional<Paciente> paciente) {
        if ( paciente == null ) {
            return null;
        }

        Paciente paciente1 = new Paciente();

        return paciente1;
    }

    @Override
    public List<PacienteResponse> toPacienteReponseList(List<Paciente> paciente) {
        if ( paciente == null ) {
            return null;
        }

        List<PacienteResponse> list = new ArrayList<PacienteResponse>( paciente.size() );
        for ( Paciente paciente1 : paciente ) {
            list.add( toPacienteResponse( paciente1 ) );
        }

        return list;
    }

    @Override
    public Paciente toPacienteEntity(PacienteRequest pacienteRequest) {
        if ( pacienteRequest == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setFirstName( pacienteRequest.getFirstName() );
        paciente.setLastName( pacienteRequest.getLastName() );
        paciente.setCpf( pacienteRequest.getCpf() );
        paciente.setDataNascimento( pacienteRequest.getDataNascimento() );
        paciente.setTelefone( pacienteRequest.getTelefone() );
        paciente.setEmail( pacienteRequest.getEmail() );
        paciente.setPassword( pacienteRequest.getPassword() );

        return paciente;
    }
}

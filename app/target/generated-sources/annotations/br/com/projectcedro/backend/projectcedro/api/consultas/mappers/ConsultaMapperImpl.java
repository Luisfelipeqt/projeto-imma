package br.com.projectcedro.backend.projectcedro.api.consultas.mappers;

import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaRequest;
import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaResponse;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteRequest;
import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
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
public class ConsultaMapperImpl implements ConsultaMapper {

    @Override
    public ConsultaRequest toConsultaRequest(Consulta consulta) {
        if ( consulta == null ) {
            return null;
        }

        ConsultaRequest consultaRequest = new ConsultaRequest();

        consultaRequest.setData( consulta.getData() );
        consultaRequest.setMedico( consulta.getMedico() );
        consultaRequest.setPaciente( consulta.getPaciente() );

        return consultaRequest;
    }

    @Override
    public ConsultaResponse toConsultaResponse(Consulta consulta) {
        if ( consulta == null ) {
            return null;
        }

        ConsultaResponse consultaResponse = new ConsultaResponse();

        consultaResponse.setId( consulta.getId() );
        consultaResponse.setData( consulta.getData() );
        consultaResponse.setMedico( medicoToMedicoRequest( consulta.getMedico() ) );
        consultaResponse.setPaciente( pacienteToPacienteRequest( consulta.getPaciente() ) );
        consultaResponse.setCreatedAt( consulta.getCreatedAt() );
        consultaResponse.setUpdatedAt( consulta.getUpdatedAt() );

        return consultaResponse;
    }

    @Override
    public Consulta toConsultaReponse(Optional<Consulta> consulta) {
        if ( consulta == null ) {
            return null;
        }

        Consulta consulta1 = new Consulta();

        return consulta1;
    }

    @Override
    public Consulta toConsultaEntity(ConsultaRequest consultaRequest) {
        if ( consultaRequest == null ) {
            return null;
        }

        Consulta consulta = new Consulta();

        consulta.setData( consultaRequest.getData() );
        consulta.setMedico( consultaRequest.getMedico() );
        consulta.setPaciente( consultaRequest.getPaciente() );

        return consulta;
    }

    @Override
    public List<ConsultaResponse> toConsultaReponseList(List<Consulta> e) {
        if ( e == null ) {
            return null;
        }

        List<ConsultaResponse> list = new ArrayList<ConsultaResponse>( e.size() );
        for ( Consulta consulta : e ) {
            list.add( toConsultaResponse( consulta ) );
        }

        return list;
    }

    protected MedicoRequest medicoToMedicoRequest(Medico medico) {
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

    protected PacienteRequest pacienteToPacienteRequest(Paciente paciente) {
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
}

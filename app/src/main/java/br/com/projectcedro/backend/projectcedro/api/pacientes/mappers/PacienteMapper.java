package br.com.projectcedro.backend.projectcedro.api.pacientes.mappers;

import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteRequest;
import br.com.projectcedro.backend.projectcedro.api.pacientes.dtos.PacienteResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PacienteMapper {

    PacienteMapper MAPPER = Mappers.getMapper(PacienteMapper.class);


    PacienteRequest toPacienteRequest(Paciente paciente);
    PacienteResponse toPacienteResponse(Paciente paciente);

    Paciente toPacienteReponse(Optional<Paciente> paciente);
    List<PacienteResponse> toPacienteReponseList(List<Paciente> paciente);


    Paciente toPacienteEntity(PacienteRequest pacienteRequest);
}

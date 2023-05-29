package br.com.projectcedro.backend.projectcedro.api.medicos.mappers;

import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoRequest;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MedicoMapper {

    MedicoMapper MAPPER = Mappers.getMapper(MedicoMapper.class);


    MedicoRequest toMedicoRequest(Medico medico);
    MedicoResponse toMedicoResponse(Medico medico);

    Medico toMedicoReponse(Optional<Medico> medico);

    Medico toMedicoEntity(MedicoRequest pacienteRequest);
    Medico toMedicoEntity(String id, MedicoRequest pacienteRequest);

    List<MedicoResponse> toMedicoReponseList(List<Medico> e);


}

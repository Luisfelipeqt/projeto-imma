package br.com.projectcedro.backend.projectcedro.api.consultas.mappers;

import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaRequest;
import br.com.projectcedro.backend.projectcedro.api.consultas.dtos.ConsultaResponse;
import br.com.projectcedro.backend.projectcedro.api.medicos.dtos.MedicoResponse;
import br.com.projectcedro.backend.projectcedro.core.entities.Consulta;
import br.com.projectcedro.backend.projectcedro.core.entities.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ConsultaMapper {

    ConsultaMapper MAPPER = Mappers.getMapper(ConsultaMapper.class);


    ConsultaRequest toConsultaRequest(Consulta consulta);
    ConsultaResponse toConsultaResponse(Consulta consulta);

    Consulta toConsultaReponse(Optional<Consulta> consulta);

    Consulta toConsultaEntity(ConsultaRequest consultaRequest);
    List<ConsultaResponse> toConsultaReponseList(List<Consulta> e);

}

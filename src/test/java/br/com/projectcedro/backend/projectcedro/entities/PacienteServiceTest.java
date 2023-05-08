package br.com.projectcedro.backend.projectcedro.entities;

import br.com.projectcedro.backend.projectcedro.repositories.PacienteRepository;
import br.com.projectcedro.backend.projectcedro.services.paciente.PacienteService;
import br.com.projectcedro.backend.projectcedro.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.projectcedro.backend.projectcedro.common.PacienteConstants.INVALID_PACIENTE;
import static br.com.projectcedro.backend.projectcedro.common.PacienteConstants.PACIENTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @Test
    void createPacient_WithValidData_ReturnsPacient() {
        when(pacienteRepository.save(PACIENTE)).thenReturn(PACIENTE); // -> Quando for salvo a instancia PACIENTE e retornar PACIENTE
        Paciente sut = pacienteService.create(PACIENTE); // -> Salvando paciente no Banco de Dados

        assertThat(sut).isEqualTo(PACIENTE); // -> Verifica se o sut é igual ao que foi salvo no banco de dados

    }


    @Test
    void createPacient_WithInvalidData_Returns_TrowsException() {
        when(pacienteRepository.save(INVALID_PACIENTE)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> pacienteService.create(INVALID_PACIENTE)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void findAllPacientes() {
        when(pacienteRepository.findAll()).thenReturn(List.of(PACIENTE));

        List<Paciente> sut = pacienteService.findAll();
        assertThat(sut).isNotEmpty();
    }

    @Test
    public void testFindAllPageable() {
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(new Paciente(
                null,
                "Luis Felipe",
                "Rodrigues",
                "058.151.473-46",
                new Date("13/02/1995"),
                "(16)99719-8565",
                "luisfelipebr1995@gmail.com"));
        pacientes.add(new Paciente(
                null,
                "Luis Felipe",
                "Rodrigues",
                "058.151.473-46",
                new Date("13/02/1995"),
                "(16)99719-8565",
                "luisfelipebr1995@gmail.com"));
        pacientes.add(new Paciente(
                null,
                "Luis Felipe",
                "Rodrigues",
                "058.151.473-46",
                new Date("13/02/1995"),
                "(16)99719-8565",
                "luisfelipebr1995@gmail.com"));
        Page<Paciente> page = new PageImpl<>(pacientes);

        when(pacienteRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Paciente> resultado = pacienteService.findAll(mock(Pageable.class));

        assertEquals(3, resultado.getContent().size());
    }

    @Test
    void testDelete_PacienteExist_DeletesPaciente() {
        Long pacienteId = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(pacienteId);
        when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.of(paciente));

        pacienteService.delete(pacienteId);

        verify(pacienteRepository, times(1)).findById(pacienteId);
        verify(pacienteRepository, times(1)).delete(paciente);
    }

    @Test
    void testDelete_PacienteNotExist_ThrowsResourceNotFoundException() {
        // Arrange
        Long pacienteId = 1L;
        when(pacienteRepository.findById(pacienteId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            pacienteService.delete(pacienteId);
        });
        verify(pacienteRepository, times(1)).findById(pacienteId);
        verify(pacienteRepository, never()).delete(any(Paciente.class));
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        Paciente result = pacienteService.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> pacienteService.findById(id));
    }
}



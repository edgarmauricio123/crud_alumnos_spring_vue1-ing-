package com.israel.alumnos;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional; // <-- FALTABA

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.israel.alumnos.controllers.AlumnoController;
import com.israel.alumnos.model.Alumno;
import com.israel.alumnos.repository.AlumnoRepository;

@WebMvcTest(AlumnoController.class)
public class debeEditarUnAlumno {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeEditarUnAlumno() throws Exception {

        Long id = 1L;

        Alumno alumnoExistente = new Alumno();
        alumnoExistente.setId(id);
        alumnoExistente.setNombre("Juan");

        Alumno alumnoEditado = new Alumno();
        alumnoEditado.setNombre("Juan Editado");
        alumnoEditado.setEmail("juaneditado@gmail.com");

        // simulamos que ya existe el id en la base de datos
        when(alumnoRepository.findById(id)).thenReturn(Optional.of(alumnoExistente));

        // simulamos guardado del alumno editado
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumnoExistente);

        mockMvc.perform(
                put("/alumnos/editar-alumnos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoEditado))
        )
        .andExpect(status().isOk());

        verify(alumnoRepository, times(1)).save(any(Alumno.class));
    }

} 

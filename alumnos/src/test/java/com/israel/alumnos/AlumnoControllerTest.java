package com.israel.alumnos;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

public class AlumnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void debeInsertarUnAlumno() throws Exception {

        Alumno alumnoNuevo = new Alumno();
        alumnoNuevo.setNombre("Juan");
        alumnoNuevo.setNumeroControl("123456");

        when(alumnoRepository.save(org.mockito.ArgumentMatchers.any(Alumno.class)))
                .thenReturn(alumnoNuevo);

        mockMvc.perform(post("/alumnos/insertar-alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(alumnoNuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }
}
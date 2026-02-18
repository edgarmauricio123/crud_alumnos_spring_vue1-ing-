package com.israel.alumnos;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.israel.alumnos.controllers.AlumnoController;
import com.israel.alumnos.model.Alumno;
import com.israel.alumnos.repository.AlumnoRepository;

@WebMvcTest(AlumnoController.class)
public class debeTraerTodosLosAlumnos {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Test
    public void debeTraerTodosLosAlumnos() throws Exception {

        Alumno alumno1 = new Alumno();
        alumno1.setId(1L);
        alumno1.setNombre("Israel");
        alumno1.setCarrera("Sistemas");

        Alumno alumno2 = new Alumno();
        alumno2.setId(2L);
        alumno2.setNombre("Juan");
        alumno2.setCarrera("Informatica");

        when(alumnoRepository.findAll())
            .thenReturn(Arrays.asList(alumno1, alumno2));

        mockMvc.perform(get("/alumnos/traer-alumnos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Israel")));
    }
}

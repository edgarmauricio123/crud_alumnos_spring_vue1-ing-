package com.israel.alumnos;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// Importacion de las class reales 
import com.israel.alumnos.controllers.AlumnoController;
import com.israel.alumnos.repository.AlumnoRepository;

@WebMvcTest(AlumnoController.class)
public class DebeEliminarUnAlumnoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoRepository alumnoRepository;

    @Test 
    void debeEliminarUnAlumno() throws Exception {
        Long idParaEliminar = 1L;

        mockMvc.perform(delete("/alumnos/eliminar-alumnos/{id}", idParaEliminar)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(alumnoRepository, times(1)).deleteById(idParaEliminar);
    }
}

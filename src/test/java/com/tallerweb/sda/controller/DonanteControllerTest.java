package com.tallerweb.sda.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.service.DonanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DonanteControllerTest {

    @Mock
    private DonanteService donanteService;

    @InjectMocks
    private DonanteController donanteController;

    @Autowired
    private MockMvc mockMvc;

    private Donante donante;

    @BeforeEach
    public void setUp() {
        donante = new Donante();
        donante.setId(1L);
        donante.setNombre("Pedro");
        donante.setEmail("pedro@example.com");

        // Configurar MockMvc sin @MockBean
        mockMvc = MockMvcBuilders.standaloneSetup(donanteController).build();
    }

    @Test
    public void testGetAllDonantes() throws Exception {
        when(donanteService.getAllDonantes()).thenReturn(Arrays.asList(donante));

        mockMvc.perform(get("/api/donantes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }

    @Test
    public void testGetDonanteById() throws Exception {
        when(donanteService.getDonanteById(1L)).thenReturn(Optional.of(donante));

        mockMvc.perform(get("/api/donantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    @Test
    public void testCreateDonante() throws Exception {
        when(donanteService.saveDonante(any(Donante.class))).thenReturn(donante);

        mockMvc.perform(post("/api/donantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(donante)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }

    @Test
    public void testDeleteDonante() throws Exception {
        Long id = 1L;
        donante.setId(id);
        when(donanteService.getDonanteById(id)).thenReturn(Optional.of(donante));

        mockMvc.perform(delete("/api/donantes/{id}", id))
                .andExpect(status().isNoContent());

        verify(donanteService, times(1)).deleteDonante(id);
    }
}
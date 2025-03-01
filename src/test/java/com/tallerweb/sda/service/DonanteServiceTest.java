package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.repository.DonanteRepository;
import com.tallerweb.sda.service.impl.DonanteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DonanteServiceTest {

    @Mock
    private DonanteRepository donanteRepository;

    @InjectMocks
    private DonanteServiceImpl donanteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDonantes() {
        Donante donante1 = new Donante("Pedro Sánchez", "pedro@example.com", "123456789");
        Donante donante2 = new Donante("Ana López", "ana@example.com", "987654321");

        when(donanteRepository.findAll()).thenReturn(Arrays.asList(donante1, donante2));

        List<Donante> result = donanteService.getAllDonantes();
        assertEquals(2, result.size());
        assertEquals("Pedro Sánchez", result.get(0).getNombre());
    }

    @Test
    public void testGetDonanteById() {
        Donante donante = new Donante("Pedro Sánchez", "pedro@example.com", "123456789");
        when(donanteRepository.findById(1L)).thenReturn(Optional.of(donante));

        Optional<Donante> result = donanteService.getDonanteById(1L);
        assertTrue(result.isPresent());
        assertEquals("Pedro Sánchez", result.get().getNombre());
    }

    @Test
    public void testSaveDonante() {
        Donante donante = new Donante("Pedro Sánchez", "pedro@example.com", "123456789");
        when(donanteRepository.save(any(Donante.class))).thenReturn(donante);

        Donante result = donanteService.saveDonante(donante);
        assertEquals("Pedro Sánchez", result.getNombre());
        verify(donanteRepository, times(1)).save(donante);
    }

    @Test
    public void testDeleteDonante() {
        long id = 1L;
        doNothing().when(donanteRepository).deleteById(id);

        donanteService.deleteDonante(id);

        verify(donanteRepository, times(1)).deleteById(id);
    }
}
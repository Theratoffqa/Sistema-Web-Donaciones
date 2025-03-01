package com.tallerweb.sda.service.impl;

import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.repository.DonanteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DonanteServiceImplTest {

    @Mock
    DonanteRepository donanteRepository;

    @InjectMocks
    DonanteServiceImpl donanteServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDonantes() {
        Donante donante = new Donante("nombre", "email", "telefono");

        when(donanteRepository.findAll()).thenReturn(List.of(donante));

        List<Donante> result = donanteServiceImpl.getAllDonantes();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(donante, result.get(0));
    }

    @Test
    void testGetDonanteById() {
        Donante donante = new Donante("nombre", "email", "telefono");
        donante.setId(1L);

        when(donanteRepository.findById(anyLong())).thenReturn(Optional.of(donante));

        Optional<Donante> result = donanteServiceImpl.getDonanteById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(donante, result.get());
    }

    @Test
    void testSaveDonante() {
        Donante donante = new Donante("nombre", "email", "telefono");

        when(donanteRepository.save(any(Donante.class))).thenReturn(donante);

        Donante result = donanteServiceImpl.saveDonante(donante);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(donante, result);
    }

    @Test
    void testDeleteDonante() {
        doNothing().when(donanteRepository).deleteById(anyLong());

        donanteServiceImpl.deleteDonante(1L);

        verify(donanteRepository, times(1)).deleteById(1L);
    }
}
package com.tallerweb.sda.service.impl;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.repository.DonacionRepository;
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

class DonacionServiceImplTest {

    @Mock
    DonacionRepository donacionRepository;

    @InjectMocks
    DonacionServiceImpl donacionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDonaciones() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.findAll()).thenReturn(List.of(donacion));

        List<Donacion> result = donacionServiceImpl.getAllDonaciones();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testGetDonacionById() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.findById(anyLong())).thenReturn(Optional.of(donacion));

        Optional<Donacion> result = donacionServiceImpl.getDonacionById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetDonacionesByDonante() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.findByDonanteId(anyLong())).thenReturn(List.of(donacion));

        List<Donacion> result = donacionServiceImpl.getDonacionesByDonante(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testSaveDonacion() {
        Donante donante = new Donante("nombre", "email", "telefono");
        Donacion donacion = new Donacion();
        donacion.setId(1L);

        when(donacionRepository.save(any(Donacion.class))).thenReturn(donacion);

        Donacion result = donacionServiceImpl.saveDonacion(donacion, donante);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void testDeleteDonacion() {
        doNothing().when(donacionRepository).deleteById(anyLong());

        donacionServiceImpl.deleteDonacion(1L);

        verify(donacionRepository, times(1)).deleteById(1L);
    }
}

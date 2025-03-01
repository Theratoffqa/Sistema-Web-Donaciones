package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.service.impl.DonacionServiceImpl;
import com.tallerweb.sda.repository.DonacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DonacionServiceTest {

    @Mock
    private DonacionRepository donacionRepository;

    @InjectMocks
    private DonacionServiceImpl donacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveDonacion() {
        Donante donante = new Donante();
        donante.setId(1L);

        Donacion donacion = new Donacion();
        donacion.setDescripcion("Alimentos no perecibles");

        when(donacionRepository.save(any(Donacion.class))).thenReturn(donacion);

        Donacion resultado = donacionService.saveDonacion(donacion, donante);
        assertEquals("Alimentos no perecibles", resultado.getDescripcion());
        verify(donacionRepository, times(1)).save(any(Donacion.class));
    }

    @Test
    void testGetDonacionById() {
        Donacion donacion = new Donacion();
        donacion.setId(1L);
        donacion.setDescripcion("Medicamentos");

        when(donacionRepository.findById(1L)).thenReturn(Optional.of(donacion));

        Optional<Donacion> resultado = donacionService.getDonacionById(1L);
        assertEquals("Medicamentos", resultado.get().getDescripcion());
    }
}
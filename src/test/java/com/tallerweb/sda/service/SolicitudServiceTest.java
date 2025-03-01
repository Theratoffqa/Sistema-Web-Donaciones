package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.model.Solicitud;
import com.tallerweb.sda.repository.SolicitudRepository;
import com.tallerweb.sda.service.impl.SolicitudServiceImpl;
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

public class SolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @InjectMocks
    private SolicitudServiceImpl solicitudService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSolicitudes() {
        Solicitud solicitud1 = new Solicitud();
        Solicitud solicitud2 = new Solicitud();

        when(solicitudRepository.findAll()).thenReturn(Arrays.asList(solicitud1, solicitud2));

        List<Solicitud> result = solicitudService.getAllSolicitudes();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetSolicitudById() {
        Solicitud solicitud = new Solicitud();
        when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));

        Optional<Solicitud> result = solicitudService.getSolicitudById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    public void testGetSolicitudesByBeneficiario() {
        Solicitud solicitud1 = new Solicitud();
        Solicitud solicitud2 = new Solicitud();

        when(solicitudRepository.findByBeneficiarioId(1L)).thenReturn(Arrays.asList(solicitud1, solicitud2));

        List<Solicitud> result = solicitudService.getSolicitudesByBeneficiario(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveSolicitud() {
        Solicitud solicitud = new Solicitud();
        Beneficiario beneficiario = new Beneficiario();
        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        Solicitud result = solicitudService.saveSolicitud(solicitud, beneficiario);
        assertNotNull(result);
        verify(solicitudRepository, times(1)).save(solicitud);
    }

    @Test
    public void testDeleteSolicitud() {
        long id = 1L;
        doNothing().when(solicitudRepository).deleteById(id);

        solicitudService.deleteSolicitud(id);

        verify(solicitudRepository, times(1)).deleteById(id);
    }
}
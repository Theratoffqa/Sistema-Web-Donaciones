package com.tallerweb.sda.service.impl;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.model.Solicitud;
import com.tallerweb.sda.repository.SolicitudRepository;
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

class SolicitudServiceImplTest {

    @Mock
    SolicitudRepository solicitudRepository;

    @InjectMocks
    SolicitudServiceImpl solicitudServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSolicitudes() {
        Solicitud solicitud = new Solicitud();

        when(solicitudRepository.findAll()).thenReturn(List.of(solicitud));

        List<Solicitud> result = solicitudServiceImpl.getAllSolicitudes();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(solicitud, result.get(0));
    }

    @Test
    void testGetSolicitudById() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(1L);

        when(solicitudRepository.findById(anyLong())).thenReturn(Optional.of(solicitud));

        Optional<Solicitud> result = solicitudServiceImpl.getSolicitudById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(solicitud, result.get());
    }

    @Test
    void testGetSolicitudesByBeneficiario() {
        Solicitud solicitud = new Solicitud();
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1L);
        solicitud.setBeneficiario(beneficiario);

        when(solicitudRepository.findByBeneficiarioId(anyLong())).thenReturn(List.of(solicitud));

        List<Solicitud> result = solicitudServiceImpl.getSolicitudesByBeneficiario(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(solicitud, result.get(0));
    }

    @Test
    void testSaveSolicitud() {
        Solicitud solicitud = new Solicitud();
        Beneficiario beneficiario = new Beneficiario();

        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        Solicitud result = solicitudServiceImpl.saveSolicitud(solicitud, beneficiario);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(solicitud, result);
    }

    @Test
    void testDeleteSolicitud() {
        doNothing().when(solicitudRepository).deleteById(anyLong());

        solicitudServiceImpl.deleteSolicitud(1L);

        verify(solicitudRepository, times(1)).deleteById(1L);
    }
}
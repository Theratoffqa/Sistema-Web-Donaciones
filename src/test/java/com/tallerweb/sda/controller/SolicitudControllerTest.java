package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.model.Solicitud;
import com.tallerweb.sda.service.BeneficiarioService;
import com.tallerweb.sda.service.SolicitudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class SolicitudControllerTest {
    @Mock
    SolicitudService solicitudService;
    @Mock
    BeneficiarioService beneficiarioService;
    @InjectMocks
    SolicitudController solicitudController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSolicitudes() {
        Solicitud solicitudMock = new Solicitud();
        when(solicitudService.getAllSolicitudes()).thenReturn(List.of(solicitudMock));

        List<Solicitud> result = solicitudController.getAllSolicitudes();
        Assertions.assertEquals(List.of(solicitudMock), result);
    }

    @Test
    void testGetSolicitudById() {
        when(solicitudService.getSolicitudById(anyLong())).thenReturn(null);

        Optional<Solicitud> result = solicitudController.getSolicitudById(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetSolicitudesByBeneficiario() {
        Solicitud solicitudMock = new Solicitud(); // Crea un objeto solicitud de ejemplo
        when(solicitudService.getSolicitudesByBeneficiario(anyLong())).thenReturn(List.of(solicitudMock));

        List<Solicitud> result = solicitudController.getSolicitudesByBeneficiario(1L);

        Assertions.assertEquals(List.of(solicitudMock), result); // Usa el mismo objeto para la comparación
    }

    @Test
    void testCreateSolicitud() {
        Solicitud solicitudMock = new Solicitud();
        when(solicitudService.saveSolicitud(any(Solicitud.class), any(Beneficiario.class)))
                .thenReturn(solicitudMock);
        when(beneficiarioService.getBeneficiarioById(anyLong()))
                .thenReturn(Optional.of(new Beneficiario())); // Simula un beneficiario válido

        ResponseEntity<Solicitud> result = solicitudController.createSolicitud(1L, solicitudMock);

        Assertions.assertEquals(ResponseEntity.ok(solicitudMock), result);
    }

    @Test
    void testDeleteSolicitud() {
        solicitudController.deleteSolicitud(Long.valueOf(1));
        verify(solicitudService).deleteSolicitud(anyLong());
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme
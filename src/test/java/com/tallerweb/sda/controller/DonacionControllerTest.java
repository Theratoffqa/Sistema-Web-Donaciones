package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Donacion;
import com.tallerweb.sda.model.Donante;
import com.tallerweb.sda.service.DonacionService;
import com.tallerweb.sda.service.DonanteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class DonacionControllerTest {
    @Mock
    DonacionService donacionService;
    @Mock
    DonanteService donanteService;
    @InjectMocks
    DonacionController donacionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDonaciones() {
        Donacion donacionMock = new Donacion();
        when(donacionService.getAllDonaciones()).thenReturn(List.of(donacionMock));

        List<Donacion> result = donacionController.getAllDonaciones();
        Assertions.assertEquals(List.of(donacionMock), result);
    }

    @Test
    void testGetDonacionById() {
        Donacion donacionMock = new Donacion();
        when(donacionService.getDonacionById(anyLong())).thenReturn(Optional.of(donacionMock));

        Optional<Donacion> result = donacionController.getDonacionById(1L);
        Assertions.assertEquals(Optional.of(donacionMock), result);
    }

    @Test
    void testGetDonacionesByDonante() {
        Donacion donacionMock = new Donacion();
        when(donacionService.getDonacionesByDonante(anyLong())).thenReturn(List.of(donacionMock));

        List<Donacion> result = donacionController.getDonacionesByDonante(1L);
        Assertions.assertEquals(List.of(donacionMock), result);
    }

    @Test
    void testCreateDonacion() {
        Donacion donacionMock = new Donacion();
        Donante donanteMock = new Donante("nombre", "email", "telefono");

        when(donanteService.getDonanteById(anyLong())).thenReturn(Optional.of(donanteMock));
        when(donacionService.saveDonacion(any(Donacion.class), any(Donante.class))).thenReturn(donacionMock);

        ResponseEntity<Donacion> result = donacionController.createDonacion(1L, new Donacion());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(donacionMock, result.getBody());
    }

    @Test
    void testDeleteDonacion() {
        doNothing().when(donacionService).deleteDonacion(anyLong());

        donacionController.deleteDonacion(1L);
        verify(donacionService, times(1)).deleteDonacion(1L);
    }
}
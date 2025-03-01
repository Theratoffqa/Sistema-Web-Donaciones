package com.tallerweb.sda.controller;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.service.BeneficiarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class BeneficiarioControllerTest {
    @Mock
    BeneficiarioService beneficiarioService;
    @InjectMocks
    BeneficiarioController beneficiarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBeneficiarios() {
        Beneficiario beneficiarioMock = new Beneficiario();
        when(beneficiarioService.getAllBeneficiarios()).thenReturn(List.of(beneficiarioMock));

        List<Beneficiario> result = beneficiarioController.getAllBeneficiarios();
        Assertions.assertEquals(List.of(beneficiarioMock), result);
    }

    @Test
    void testGetBeneficiarioById() {
        Beneficiario beneficiarioMock = new Beneficiario();
        when(beneficiarioService.getBeneficiarioById(anyLong())).thenReturn(Optional.of(beneficiarioMock));

        Optional<Beneficiario> result = beneficiarioController.getBeneficiarioById(1L);
        Assertions.assertEquals(Optional.of(beneficiarioMock), result);
    }

    @Test
    void testCreateBeneficiario() {
        Beneficiario beneficiarioMock = new Beneficiario();
        when(beneficiarioService.saveBeneficiario(any(Beneficiario.class))).thenReturn(beneficiarioMock);

        Beneficiario result = beneficiarioController.createBeneficiario(new Beneficiario());
        Assertions.assertEquals(beneficiarioMock, result);
    }

    @Test
    void testDeleteBeneficiario() {
        doNothing().when(beneficiarioService).deleteBeneficiario(anyLong());

        beneficiarioController.deleteBeneficiario(1L);
        verify(beneficiarioService, times(1)).deleteBeneficiario(1L);
    }
}
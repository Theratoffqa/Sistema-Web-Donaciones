package com.tallerweb.sda.service;

import com.tallerweb.sda.model.Beneficiario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BeneficiarioServiceTest {

    @Mock
    private BeneficiarioService beneficiarioService;

    @InjectMocks
    private BeneficiarioServiceTest serviceTest;

    private Beneficiario beneficiario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        beneficiario = new Beneficiario();
        beneficiario.setId(1L);
        beneficiario.setNombre("Juan Pérez");
    }

    @Test
    public void testGetAllBeneficiarios() {
        List<Beneficiario> beneficiarios = Arrays.asList(beneficiario);
        when(beneficiarioService.getAllBeneficiarios()).thenReturn(beneficiarios);

        List<Beneficiario> result = beneficiarioService.getAllBeneficiarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getNombre());
        verify(beneficiarioService, times(1)).getAllBeneficiarios();
    }

    @Test
    public void testGetBeneficiarioById() {
        when(beneficiarioService.getBeneficiarioById(1L)).thenReturn(Optional.of(beneficiario));

        Optional<Beneficiario> result = beneficiarioService.getBeneficiarioById(1L);

        assertTrue(result.isPresent());
        assertEquals("Juan Pérez", result.get().getNombre());
        verify(beneficiarioService, times(1)).getBeneficiarioById(1L);
    }

    @Test
    public void testSaveBeneficiario() {
        when(beneficiarioService.saveBeneficiario(beneficiario)).thenReturn(beneficiario);

        Beneficiario result = beneficiarioService.saveBeneficiario(beneficiario);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
        verify(beneficiarioService, times(1)).saveBeneficiario(beneficiario);
    }

    @Test
    public void testDeleteBeneficiario() {
        doNothing().when(beneficiarioService).deleteBeneficiario(1L);

        beneficiarioService.deleteBeneficiario(1L);

        verify(beneficiarioService, times(1)).deleteBeneficiario(1L);
    }
}
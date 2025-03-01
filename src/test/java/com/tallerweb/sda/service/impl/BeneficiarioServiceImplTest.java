package com.tallerweb.sda.service.impl;

import com.tallerweb.sda.model.Beneficiario;
import com.tallerweb.sda.repository.BeneficiarioRepository;
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

class BeneficiarioServiceImplTest {

    @Mock
    BeneficiarioRepository beneficiarioRepository;

    @InjectMocks
    BeneficiarioServiceImpl beneficiarioServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBeneficiarios() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1L);
        beneficiario.setNombre("Test Beneficiario");

        when(beneficiarioRepository.findAll()).thenReturn(List.of(beneficiario));

        List<Beneficiario> result = beneficiarioServiceImpl.getAllBeneficiarios();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Test Beneficiario", result.get(0).getNombre());
    }

    @Test
    void testGetBeneficiarioById() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(1L);

        when(beneficiarioRepository.findById(anyLong())).thenReturn(Optional.of(beneficiario));

        Optional<Beneficiario> result = beneficiarioServiceImpl.getBeneficiarioById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());
    }

    @Test
    void testSaveBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNombre("Nuevo Beneficiario");

        when(beneficiarioRepository.save(any(Beneficiario.class))).thenReturn(beneficiario);

        Beneficiario result = beneficiarioServiceImpl.saveBeneficiario(beneficiario);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Nuevo Beneficiario", result.getNombre());
    }

    @Test
    void testDeleteBeneficiario() {
        doNothing().when(beneficiarioRepository).deleteById(anyLong());

        beneficiarioServiceImpl.deleteBeneficiario(1L);

        verify(beneficiarioRepository, times(1)).deleteById(1L);
    }
}